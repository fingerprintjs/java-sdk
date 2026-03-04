---
"java-sdk": major
---

Migrate the Java SDK to Server API v4 and release `8.0.0`.

### Breaking changes

- Upgraded from Server API v3 to [v4](https://docs.fingerprint.com/reference/server-api-v4).
- Changed the root package from `com.fingerprint` to `com.fingerprint.v4`.
- Removed the `GetVisits` and `GetRelatedVisitors` operations.
- Updated the `GetEvent` and `SearchEvents` models to use a more concise shape and clearer names.

### Improvements

- Use the Apache HTTP client connector by default to support the PATCH HTTP method.
- Improved JitPack compatibility by updating the build environment handling.
- Made the JitPack environment variable check case-insensitive.

For migration steps, see the [Java SDK v8 migration guide](https://docs.fingerprint.com/reference/java-server-sdk#migration-guide-for-java-sdk-v8-0-0).
