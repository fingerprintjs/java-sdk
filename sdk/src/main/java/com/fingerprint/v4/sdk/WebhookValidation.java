package com.fingerprint.v4.sdk;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Hex;

public class WebhookValidation {

  private static final String ALGORITHM = "HmacSHA256";
  private static final String SIGNATURE_VERSION = "v1";
  private static final int SIGNATURE_LENGTH = 64;

  /**
   * Verifies the HMAC signature extracted from the "fpjs-event-signature" header of the incoming request.
   * This is a part of the webhook signing process, which is available only for enterprise customers.
   * @param header The value of the "fpjs-event-signature" header
   * @param data The raw data of the incoming request
   * @param secret The secret key used to sign the request
   * @return true if the signature is valid
   * @throws NoSuchAlgorithmException in case HMAC-SHA-256 isn't available
   * @throws IllegalArgumentException if an invalid key is provided
   */
  public static boolean isSignatureValid(String header, byte[] data, String secret)
      throws NoSuchAlgorithmException {
    if (secret == null || secret.isEmpty()) {
      throw new IllegalArgumentException("Webhook secret must not be empty");
    }

    byte[] expected = null;

    for (String signature : header.split(",")) {
      String[] parts = signature.split("=", -1);
      if (parts.length != 2
          || !parts[0].equals(SIGNATURE_VERSION)
          || parts[1].length() != SIGNATURE_LENGTH) {
        continue;
      }

      // Header can contain many signatures, compute the HMAC only once and reuse it.
      if (expected == null) {
        expected = computeSignature(data, secret);
        if (expected == null) {
          return false;
        }
      }

      if (MessageDigest.isEqual(expected, parts[1].getBytes(StandardCharsets.UTF_8))) {
        return true;
      }
    }
    return false;
  }

  /**
   * Returns the lowercase hex HMAC-SHA256 of the data as ASCII bytes, or null for an unusable key.
   */
  private static byte[] computeSignature(byte[] data, String secret)
      throws NoSuchAlgorithmException {
    SecretKeySpec spec = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), ALGORITHM);
    Mac mac = Mac.getInstance(ALGORITHM);
    try {
      mac.init(spec);
    } catch (InvalidKeyException e) {
      return null;
    }
    return Hex.encodeHexString(mac.doFinal(data)).getBytes(StandardCharsets.US_ASCII);
  }
}
