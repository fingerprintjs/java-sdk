package com.fingerprint.v4;

import static org.junit.jupiter.api.Assertions.*;

import com.fingerprint.v4.api.FingerprintApi;
import com.fingerprint.v4.sdk.ApiClient;
import com.fingerprint.v4.sdk.Configuration;
import com.fingerprint.v4.sdk.Region;
import com.fingerprint.v4.sdk.auth.Authentication;
import com.fingerprint.v4.sdk.auth.HttpBearerAuth;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

/** The deprecated constructors must keep using the shared default API client. */
@SuppressWarnings("deprecation")
public class GlobalStateRelianceTest {

  private static String bearerToken(ApiClient client) {
    for (Authentication auth : client.getAuthentications().values()) {
      if (auth instanceof HttpBearerAuth) {
        return ((HttpBearerAuth) auth).getBearerToken();
      }
    }
    return null;
  }

  @AfterEach
  public void resetDefaultApiClient() {
    Configuration.setDefaultApiClient(null);
  }

  // App bootstrap creates the API with a key, then other code uses the no-arg constructor
  // (e.g. a DI provider or a helper) and expects it to be authenticated.
  @Test
  public void noArgConstructorReusesKeyFromEarlierInstance() {
    new FingerprintApi("secret-key", Region.EUROPE);

    FingerprintApi later = new FingerprintApi();

    assertEquals("secret-key", bearerToken(later.getApiClient()));
    assertEquals(Region.EUROPE.toString(), later.getApiClient().getBasePath());
  }

  // App tunes the default client up front, then constructs the API with a key and expects
  // the tuning to apply.
  @Test
  public void defaultClientTuningAppliesToKeyedInstance() {
    Configuration.getDefaultApiClient().setConnectTimeout(1234);

    FingerprintApi api = new FingerprintApi("secret-key");

    assertEquals(1234, api.getApiClient().getConnectTimeout());
  }
}
