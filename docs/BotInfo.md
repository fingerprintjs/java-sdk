

# BotInfo

Extended bot information.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**category** | **String** | The type and purpose of the bot. |  |
|**provider** | **String** | The organization or company operating the bot. |  |
|**providerUrl** | **String** | The URL of the bot provider's website. |  [optional] |
|**name** | **String** | The specific name or identifier of the bot. |  |
|**identity** | [**IdentityEnum**](#IdentityEnum) | The verification status of the bot's identity:  * `verified` - well-known bot with publicly verifiable identity, directed by the bot provider.  * `signed` - bot that signs its platform via Web Bot Auth, directed by the bot provider’s customers.  * `spoofed` - bot that claims a public identity but fails verification.  * `unknown` - bot that does not publish a verifiable identity.  |  |
|**confidence** | [**ConfidenceEnum**](#ConfidenceEnum) | Confidence level of the bot identification. |  |



## Enum: IdentityEnum

| Name | Value |
|---- | -----|
| VERIFIED | "verified" |
| SIGNED | "signed" |
| SPOOFED | "spoofed" |
| UNKNOWN | "unknown" |
| UNSUPPORTED_VALUE_SDK_UPGRADE_REQUIRED | "unsupported_value_sdk_upgrade_required" |



## Enum: ConfidenceEnum

| Name | Value |
|---- | -----|
| LOW | "low" |
| MEDIUM | "medium" |
| HIGH | "high" |
| UNSUPPORTED_VALUE_SDK_UPGRADE_REQUIRED | "unsupported_value_sdk_upgrade_required" |



