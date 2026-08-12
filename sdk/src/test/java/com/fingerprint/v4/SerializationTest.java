package com.fingerprint.v4;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fingerprint.v4.model.ErrorCode;
import com.fingerprint.v4.model.ErrorResponse;
import com.fingerprint.v4.model.Event;
import com.fingerprint.v4.model.EventRuleAction;
import com.fingerprint.v4.sdk.JSON;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SerializationTest {

  private static final String ERRORS_DIR = "mocks/errors";
  private static final String EVENTS_DIR = "mocks/events";

  private InputStream getFileAsIOStream(final String fileName) {
    InputStream ioStream = this.getClass().getClassLoader().getResourceAsStream(fileName);

    if (ioStream == null) {
      throw new IllegalArgumentException(fileName + " is not found");
    }
    return ioStream;
  }

  private static ObjectMapper getMapper() {
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());
    mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
    return mapper;
  }

  @Test
  public void deserializeEventWithUnknownBotResultValue() throws IOException {
    ObjectMapper sdkObjectMapper = JSON.getDefault().getMapper();

    ObjectNode eventNode =
        sdkObjectMapper.readValue(
            getFileAsIOStream("mocks/events/get_event_200.json"), ObjectNode.class);

    // Set bot to an unknown enum value
    eventNode.put("bot", "unknown_future_value");

    assertDoesNotThrow(
        () -> {
          // Convert the modified ObjectNode back to an Event object to test deserialization
          sdkObjectMapper.treeToValue(eventNode, Event.class);
        });
  }

  @Test
  public void deserializeEventWithUnknownSdkPlatformValue() throws IOException {
    ObjectMapper sdkObjectMapper = JSON.getDefault().getMapper();

    ObjectNode eventNode =
        sdkObjectMapper.readValue(
            getFileAsIOStream("mocks/events/get_event_200.json"), ObjectNode.class);

    eventNode.withObject("/sdk").put("platform", "unknown_future_value");

    assertDoesNotThrow(
        () -> {
          // Convert the modified ObjectNode back to an Event object to test deserialization
          sdkObjectMapper.treeToValue(eventNode, Event.class);
        });
  }

  @Test
  public void deserializeEventWithUnknownRuleActionTypeValue() throws IOException {
    ObjectMapper sdkObjectMapper = JSON.getDefault().getMapper();

    ObjectNode eventNode =
        sdkObjectMapper.readValue(
            getFileAsIOStream("mocks/events/get_event_ruleset_200.json"), ObjectNode.class);

    eventNode.withObject("/rule_action").put("type", "unknown_future_value");

    // Convert the modified ObjectNode back to an Event object to test deserialization
    Event event = sdkObjectMapper.treeToValue(eventNode, Event.class);

    assertInstanceOf(EventRuleAction.UnknownEventRuleAction.class, event.getRuleAction());
  }

  @TestFactory
  public Stream<DynamicTest> deserializeErrorResponses() {
    ObjectMapper sdkObjectMapper = JSON.getDefault().getMapper();

    return listMockFileNames(ERRORS_DIR, name -> true).stream()
        .map(
            fileName ->
                DynamicTest.dynamicTest(
                    fileName,
                    () -> {
                      String resource = ERRORS_DIR + "/" + fileName;

                      ObjectNode errorNode =
                          sdkObjectMapper.readValue(getFileAsIOStream(resource), ObjectNode.class);
                      ErrorResponse errorResponse =
                          sdkObjectMapper.readValue(
                              getFileAsIOStream(resource), ErrorResponse.class);

                      assertNotNull(errorResponse.getError());
                      assertNotEquals(
                          ErrorCode.UNSUPPORTED_VALUE_SDK_UPGRADE_REQUIRED,
                          errorResponse.getError().getCode(),
                          "Unknown error code in " + resource);
                      assertEquals(
                          errorNode.at("/error/code").asText(),
                          errorResponse.getError().getCode().getValue());
                      assertEquals(
                          errorNode.at("/error/message").asText(),
                          errorResponse.getError().getMessage());

                      getMapper().writeValueAsString(errorResponse);
                    }));
  }

  @TestFactory
  public Stream<DynamicTest> deserializeSerializeEvents() {
    ObjectMapper sdkObjectMapper = JSON.getDefault().getMapper();

    return listMockFileNames(EVENTS_DIR, name -> name.startsWith("get_event")).stream()
        .map(
            fileName ->
                DynamicTest.dynamicTest(
                    fileName,
                    () -> {
                      String resource = EVENTS_DIR + "/" + fileName;

                      ObjectNode eventNode =
                          sdkObjectMapper.readValue(getFileAsIOStream(resource), ObjectNode.class);
                      Event event =
                          sdkObjectMapper.readValue(getFileAsIOStream(resource), Event.class);

                      assertEquals(eventNode.get("event_id").asText(), event.getEventId());
                      assertEquals(
                          eventNode.get("timestamp").asLong(), event.getTimestamp().longValue());

                      getMapper().writeValueAsString(event);
                    }));
  }

  private List<String> listMockFileNames(String directoryName, Predicate<String> fileNameFilter) {
    URL directory = this.getClass().getClassLoader().getResource(directoryName);

    if (directory == null) {
      throw new IllegalStateException(directoryName + " is not found");
    }

    File[] files;
    try {
      files =
          new File(directory.toURI())
              .listFiles((dir, name) -> name.endsWith(".json") && fileNameFilter.test(name));
    } catch (URISyntaxException e) {
      throw new IllegalStateException(e);
    }

    if (files == null || files.length == 0) {
      throw new IllegalStateException("No mocks found in " + directoryName);
    }

    return Arrays.stream(files).map(File::getName).sorted().collect(Collectors.toList());
  }
}
