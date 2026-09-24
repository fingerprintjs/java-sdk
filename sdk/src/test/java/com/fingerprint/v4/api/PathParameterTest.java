package com.fingerprint.v4.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.fingerprint.v4.model.EventUpdate;
import com.fingerprint.v4.sdk.ApiClient;
import com.fingerprint.v4.sdk.ApiException;
import com.sun.net.httpserver.HttpServer;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

/**
 * Tests for how an ID given to a public API method travels into the request URL.
 */
public class PathParameterTest {

  @FunctionalInterface
  private interface ApiCall {
    void call(FingerprintApi api, String id) throws ApiException;
  }

  /** An operation that takes an ID in the path. */
  private static final class Endpoint {
    final String name;

    /** The parameter name as it appears in the rejection message. */
    final String param;

    /** The path the ID is appended to. */
    final String prefix;

    /**
     * The stub response body, so a call that reaches the server can be asserted to succeed. Empty
     * for operations that return nothing.
     */
    final String response;

    final ApiCall call;

    Endpoint(String name, String param, String prefix, String response, ApiCall call) {
      this.name = name;
      this.param = param;
      this.prefix = prefix;
      this.response = response;
      this.call = call;
    }
  }

  /** Returns every operation that takes an ID in the path. */
  private static List<Endpoint> endpoints() throws IOException {
    return Arrays.asList(
        new Endpoint(
            "getEvent",
            "eventId",
            "/events/",
            readResource("mocks/events/get_event_200.json"),
            (api, id) -> api.getEvent(id)),
        new Endpoint(
            "updateEvent",
            "eventId",
            "/events/",
            "",
            (api, id) -> api.updateEvent(id, new EventUpdate())),
        new Endpoint(
            "deleteVisitorData",
            "visitorId",
            "/visitors/",
            "",
            (api, id) -> api.deleteVisitorData(id)));
  }

  private static String readResource(String name) throws IOException {
    try (InputStream stream = PathParameterTest.class.getClassLoader().getResourceAsStream(name)) {
      if (stream == null) {
        throw new IllegalArgumentException(name + " is not found");
      }
      ByteArrayOutputStream buffer = new ByteArrayOutputStream();
      byte[] chunk = new byte[8192];
      int read;
      while ((read = stream.read(chunk)) != -1) {
        buffer.write(chunk, 0, read);
      }
      return buffer.toString(StandardCharsets.UTF_8.name());
    }
  }

  /** A stub API that records the request it was asked for. */
  private static final class StubApi implements AutoCloseable {
    private final HttpServer server;
    private final AtomicReference<URI> requestUri = new AtomicReference<>();

    StubApi(String response) throws IOException {
      byte[] body = response.getBytes(StandardCharsets.UTF_8);

      server = HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0);
      server.createContext(
          "/",
          exchange -> {
            requestUri.set(exchange.getRequestURI());

            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, body.length == 0 ? -1 : body.length);

            try (OutputStream out = exchange.getResponseBody()) {
              out.write(body);
            }
          });
      server.start();
    }

    FingerprintApi client() {
      ApiClient apiClient = new ApiClient();
      apiClient.setBasePath("http://127.0.0.1:" + server.getAddress().getPort());
      apiClient.setBearerToken("api_key");

      return new FingerprintApi(apiClient);
    }

    /** The literal request target the server received, without the query string. */
    String requestTarget() {
      URI uri = requestUri.get();

      return uri == null ? null : uri.getRawPath();
    }

    /** The request target the server received, percent-decoded. */
    String decodedPath() {
      URI uri = requestUri.get();

      return uri == null ? null : uri.getPath();
    }

    boolean wasRequested() {
      return requestUri.get() != null;
    }

    @Override
    public void close() {
      server.stop(0);
    }
  }

  private static final class Id {
    final String name;
    final String id;
    final String encoded;

    Id(String name, String id, String encoded) {
      this.name = name;
      this.id = id;
      this.encoded = encoded;
    }
  }

  /**
   * Asserts that an ID travels as a single, opaque path segment, so a value containing slashes
   * cannot inject extra segments.
   *
   * <p>Assertions are made against the literal request target the server received, since only the
   * bytes on the wire show what the API would actually be asked for.
   */
  @TestFactory
  Stream<DynamicTest> pathParameterEncoding() throws IOException {
    List<Id> ids =
        Arrays.asList(
            new Id("Path traversal", "../events", "..%2Fevents"),
            new Id("Nested path traversal", "../../events", "..%2F..%2Fevents"),
            new Id("Leading slash", "/events/123", "%2Fevents%2F123"),
            new Id("Absolute URL", "https://domain.tld/evil", "https%3A%2F%2Fdomain.tld%2Fevil"),
            new Id("Query injection", "123?limit=1", "123%3Flimit%3D1"),
            new Id("Fragment injection", "123#fragment", "123%23fragment"),
            new Id("Whitespace", "hello world", "hello%20world"),
            new Id("Empty", "", ""),
            // Only a segment made up solely of dots is a dot-segment, so anything else containing a
            // dot keeps passing through untouched.
            new Id("Regular ID with a dot", "1708102555327.NLOjmg", "1708102555327.NLOjmg"),
            new Id("Three dots", "...", "..."));

    List<DynamicTest> tests = new ArrayList<>();

    for (Endpoint endpoint : endpoints()) {
      for (Id id : ids) {
        tests.add(
            DynamicTest.dynamicTest(
                endpoint.name + ": " + id.name,
                () -> {
                  try (StubApi stub = new StubApi(endpoint.response)) {
                    endpoint.call.call(stub.client(), id.id);

                    // The ID stays inside a single, opaque segment on the wire...
                    assertEquals(endpoint.prefix + id.encoded, stub.requestTarget());
                    // ...and decodes back to exactly the ID the caller asked for.
                    assertEquals(endpoint.prefix + id.id, stub.decodedPath());
                  }
                }));
      }
    }

    return tests.stream();
  }

  /**
   * Asserts that an ID of exactly "." or ".." is refused before any request is made.
   *
   * <p>Percent-encoding cannot help here: both stay literal dots once escaped, so a URL normalizer
   * between the SDK and the API would collapse the segment and address a different endpoint than
   * the caller asked for. No resource can be identified by such a value in the first place.
   */
  @TestFactory
  Stream<DynamicTest> pathParameterDotSegmentIsRejected() throws IOException {
    List<Id> ids =
        Arrays.asList(new Id("Dot segment", ".", "."), new Id("Parent dot segment", "..", ".."));

    List<DynamicTest> tests = new ArrayList<>();

    for (Endpoint endpoint : endpoints()) {
      for (Id id : ids) {
        tests.add(
            DynamicTest.dynamicTest(
                endpoint.name + ": " + id.name,
                () -> {
                  try (StubApi stub = new StubApi(endpoint.response)) {
                    FingerprintApi api = stub.client();

                    ApiException exception =
                        assertThrows(ApiException.class, () -> endpoint.call.call(api, id.id));

                    // The message names the offending value, which is safe to embed because it
                    // is the escaped form: anything that could break out is percent-encoded.
                    assertEquals(
                        "invalid value \"" + id.encoded + "\" for path parameter " + endpoint.param,
                        exception.getMessage());
                    assertFalse(stub.wasRequested(), "no request should have been sent");
                  }
                }));
      }
    }

    return tests.stream();
  }
}
