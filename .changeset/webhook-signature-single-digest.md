---
'java-sdk': patch
---

**webhook**: `WebhookValidation.isSignatureValid` computes the body HMAC at most once per call, skips malformed signature entries, and compares signatures in constant time. An empty or `null` secret now throws `IllegalArgumentException` before any signature is checked.
