---
"java-sdk": major
---

Migrate the Java SDK to Server API v4 and release `8.0.0`.

### Breaking changes

- Upgraded from Server API v3 to [v4](https://docs.fingerprint.com/reference/server-api-v4). ([61db26f](https://github.com/fingerprintjs/java-sdk/commit/61db26fb232a5f446751470790876419ea7ff7fa))
- Changed the root package from `com.fingerprint` to `com.fingerprint.v4`. ([2e3708a](https://github.com/fingerprintjs/java-sdk/commit/2e3708a1c7a34fc40ff3899f7cbc259f47d72854))
- Removed the `GetVisits` and `GetRelatedVisitors` operations. ([61db26f](https://github.com/fingerprintjs/java-sdk/commit/61db26fb232a5f446751470790876419ea7ff7fa))
- Updated the `GetEvent` and `SearchEvents` models to use a more concise shape and clearer names. ([61db26f](https://github.com/fingerprintjs/java-sdk/commit/61db26fb232a5f446751470790876419ea7ff7fa))

### Improvements

- Use the Apache HTTP client connector by default to support the PATCH HTTP method. ([1b1f4ac](https://github.com/fingerprintjs/java-sdk/commit/1b1f4acf4e2672cd45372dac926bbafaaa0bb110))
- Improved JitPack compatibility by updating the build environment handling. ([c9f6c5c](https://github.com/fingerprintjs/java-sdk/commit/c9f6c5c0f590d9e2c49808af4f6337fa1773ffb1))
- Made the JitPack environment variable check case-insensitive. ([7d4be97](https://github.com/fingerprintjs/java-sdk/commit/7d4be977acca3803e9d6e62fc088caf1a7c4779a))

For migration steps, see the [Java SDK v8 migration guide](https://docs.fingerprint.com/reference/java-server-sdk#migration-guide-for-java-sdk-v8-0-0).
