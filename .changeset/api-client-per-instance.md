---
'java-sdk': minor
---

Add `FingerprintApi.create(apiKey[, region])` and `Configuration.createApiClient(apiKey[, region])`, which build a new `ApiClient` instead of using the shared default one. The `FingerprintApi()`, `FingerprintApi(apiKey)` and `FingerprintApi(apiKey, region)` constructors, `Configuration.getDefaultApiClient(...)` and `Configuration.setDefaultApiClient` are deprecated because they share one client across the whole process. Their behavior is unchanged.
