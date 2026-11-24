package com.fingerprint.example;

import com.fingerprint.v4.Sealed;
import com.fingerprint.v4.model.Event;
import java.io.IOException;
import java.util.Base64;

public class SealedResults {
  public static void main(String... args) {
    String SEALED_RESULT = System.getenv("BASE64_SEALED_RESULT");
    String SEALED_KEY = System.getenv("BASE64_KEY");

    final Event event;
    try {
      event =
          Sealed.unsealEventResponse(
              Base64.getDecoder().decode(SEALED_RESULT),
              new Sealed.DecryptionKey[] {
                new Sealed.DecryptionKey(
                    Base64.getDecoder().decode(SEALED_KEY), Sealed.DecryptionAlgorithm.AES_256_GCM)
              });
    } catch (Sealed.UnsealAggregateException | IOException | IllegalArgumentException e) {
      System.err.println("Failed to unseal the sealed result");
      System.exit(1);
      return;
    }

    System.out.println(event);
    System.exit(0);
  }
}
