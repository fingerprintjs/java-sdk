package com.fingerprint.v4;

import com.fingerprint.v4.sdk.WebhookValidation;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.util.Collections;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WebhookValidationTest {

  private static final String validHeader =
      "v1=1b2c16b75bd2a870c114153ccda5bcfca63314bc722fa160d690de133ccbb9db";
  private static final String secret = "secret";
  private static final byte[] data = "data".getBytes(StandardCharsets.UTF_8);

  @Test
  public void validHeaderTest() throws NoSuchAlgorithmException {
    boolean result = WebhookValidation.isSignatureValid(validHeader, data, secret);
    Assertions.assertTrue(result);
  }

  @Test
  public void invalidHeaderTest() throws NoSuchAlgorithmException {
    boolean result = WebhookValidation.isSignatureValid("v2=wrong", data, secret);
    assert !result;
  }

  @Test
  public void headerWithoutVersionTest() throws NoSuchAlgorithmException {
    boolean result = WebhookValidation.isSignatureValid("secretonly", data, secret);
    assert !result;
  }

  @Test
  public void emptySecretTest() throws NoSuchAlgorithmException {
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> {
          WebhookValidation.isSignatureValid("v1=value", data, "");
        });
  }

  @Test
  public void emptyDataTest() throws NoSuchAlgorithmException {
    boolean result = WebhookValidation.isSignatureValid(validHeader, "".getBytes(), secret);
    assert !result;
  }

  @Test
  public void validSignatureAfterOtherEntriesTest() throws NoSuchAlgorithmException {
    String header = "v1=invalid,v2=" + "0".repeat(64) + ",v1=" + "0".repeat(64) + "," + validHeader;
    Assertions.assertTrue(WebhookValidation.isSignatureValid(header, data, secret));
  }

  @Test
  public void truncatedSignatureTest() throws NoSuchAlgorithmException {
    String header = validHeader.substring(0, validHeader.length() - 1);
    Assertions.assertFalse(WebhookValidation.isSignatureValid(header, data, secret));
  }

  @Test
  public void malformedEntriesTest() throws NoSuchAlgorithmException {
    String header = "v1,v1=,=,v1=a=b,,=" + "0".repeat(64);
    Assertions.assertFalse(WebhookValidation.isSignatureValid(header, data, secret));
  }

  @Test
  public void trailingDelimiterTest() throws NoSuchAlgorithmException {
    Assertions.assertFalse(WebhookValidation.isSignatureValid(validHeader + "=", data, secret));
    Assertions.assertFalse(WebhookValidation.isSignatureValid(validHeader + "==", data, secret));
  }

  @Test
  public void nonAsciiSignatureTest() throws NoSuchAlgorithmException {
    String header = "v1=" + "\u00e9".repeat(64);
    Assertions.assertFalse(WebhookValidation.isSignatureValid(header, data, secret));
  }

  @Test
  public void emptySecretWithWellFormedHeaderTest() {
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> WebhookValidation.isSignatureValid(validHeader, data, ""));
  }

  @Test
  public void nullSecretTest() {
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> WebhookValidation.isSignatureValid(validHeader, data, null));
  }

  @Test
  public void manySignaturesAreCheckedAgainstOneDigestTest() {
    byte[] largeData = new byte[4 * 1024 * 1024];
    String header = String.join(",", Collections.nCopies(1000, "v1=" + "0".repeat(64)));

    boolean result =
        Assertions.assertTimeoutPreemptively(
            Duration.ofSeconds(2),
            () -> WebhookValidation.isSignatureValid(header, largeData, secret));
    Assertions.assertFalse(result);
  }
}
