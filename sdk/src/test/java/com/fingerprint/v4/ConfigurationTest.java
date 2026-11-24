package com.fingerprint.v4;

import static org.junit.jupiter.api.Assertions.*;

import com.fingerprint.v4.api.FingerprintApi;
import com.fingerprint.v4.sdk.ApiClient;
import com.fingerprint.v4.sdk.Configuration;
import com.fingerprint.v4.sdk.Region;
import com.fingerprint.v4.sdk.auth.Authentication;
import com.fingerprint.v4.sdk.auth.HttpBearerAuth;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ConfigurationTest {

  private static String bearerToken(ApiClient client) {
    for (Authentication auth : client.getAuthentications().values()) {
      if (auth instanceof HttpBearerAuth) {
        return ((HttpBearerAuth) auth).getBearerToken();
      }
    }
    return null;
  }

  @Test
  public void apiInstancesKeepTheirOwnKeyAndRegionTest() {
    FingerprintApi europe = new FingerprintApi("key-a", Region.EUROPE);
    FingerprintApi global = new FingerprintApi("key-b");
    FingerprintApi asia = new FingerprintApi("key-c", Region.ASIA);

    assertEquals("key-a", bearerToken(europe.getApiClient()));
    assertEquals(Region.EUROPE.toString(), europe.getApiClient().getBasePath());
    assertEquals("key-b", bearerToken(global.getApiClient()));
    assertEquals(Region.GLOBAL.toString(), global.getApiClient().getBasePath());
    assertEquals("key-c", bearerToken(asia.getApiClient()));
    assertEquals(Region.ASIA.toString(), asia.getApiClient().getBasePath());

    assertNotSame(europe.getApiClient(), global.getApiClient());
    assertNotSame(Configuration.getDefaultApiClient(), europe.getApiClient());
    assertNotSame(Configuration.getDefaultApiClient(), global.getApiClient());
  }

  @Test
  public void createApiClientDoesNotChangeDefaultClientTest() {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    String defaultToken = bearerToken(defaultClient);
    String defaultBasePath = defaultClient.getBasePath();

    ApiClient client = Configuration.createApiClient("key-d", Region.EUROPE);

    assertNotSame(defaultClient, client);
    assertEquals("key-d", bearerToken(client));
    assertEquals(Region.EUROPE.toString(), client.getBasePath());
    assertSame(defaultClient, Configuration.getDefaultApiClient());
    assertEquals(defaultToken, bearerToken(defaultClient));
    assertEquals(defaultBasePath, defaultClient.getBasePath());
  }

  @Test
  public void createApiClientResolvesRegionNamesTest() {
    assertEquals(
        Region.EUROPE.toString(), Configuration.createApiClient("key", "eu").getBasePath());
    assertEquals(
        Region.ASIA.toString(), Configuration.createApiClient("key", "asia").getBasePath());
    assertEquals(
        Region.GLOBAL.toString(), Configuration.createApiClient("key", "us").getBasePath());
    assertEquals(Region.GLOBAL.toString(), Configuration.createApiClient("key").getBasePath());
    assertEquals(
        "https://custombase.fingerprint.com",
        Configuration.createApiClient("key", "https://custombase.fingerprint.com").getBasePath());
  }
}
