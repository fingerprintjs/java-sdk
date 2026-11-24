---
'java-sdk': minor
---

Add `Configuration.createApiClient(apiKey[, region])`, which returns a new `ApiClient` instead of changing the shared default one. The `FingerprintApi(apiKey)` and `FingerprintApi(apiKey, region)` constructors now use it, so each instance keeps its own key and region. The `Configuration.getDefaultApiClient(apiKey, ...)` overloads are deprecated because they change the key and region for every user of the default client.
