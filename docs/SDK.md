

# SDK

Contains information about the SDK used to perform the request.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**platform** | [**PlatformEnum**](#PlatformEnum) | Platform of the SDK used for the identification request. |  |
|**version** | **String** | Version string of the SDK used for the identification request. For example: `\"3.12.1\"`  |  |
|**integrations** | [**List&lt;Integration&gt;**](Integration.md) |  |  [optional] |



## Enum: PlatformEnum

| Name | Value |
|---- | -----|
| JS | "js" |
| ANDROID | "android" |
| IOS | "ios" |
| UNKNOWN | "unknown" |



