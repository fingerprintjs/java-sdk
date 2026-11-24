---
'java-sdk': patch
---

**sealed**: Unseal errors no longer include decryption key. `UnsealException` identifies the failed key by its index in `decryptionKeyIndex` and `decryptionKeyDescription`, and `DecryptionKey.toString()` shows only the algorithm.
