

# BotInfo

Extended bot information.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**category** | **String** | The type and purpose of the bot. |  |
|**provider** | **String** | The organization or company operating the bot. |  |
|**providerUrl** | **String** | The URL of the bot provider&#39;s website. |  [optional] |
|**name** | **String** | The specific name or identifier of the bot. |  |
|**identity** | [**IdentityEnum**](#IdentityEnum) | The verification status of the bot&#39;s identity:  * &#x60;verified&#x60; - well-known bot with publicly verifiable identity, directed by the bot provider.  * &#x60;signed&#x60; - bot that signs its platform via Web Bot Auth, directed by the bot provider’s customers.  * &#x60;spoofed&#x60; - bot that claims a public identity but fails verification.  * &#x60;unknown&#x60; - bot that does not publish a verifiable identity.  |  |
|**confidence** | [**ConfidenceEnum**](#ConfidenceEnum) | Confidence level of the bot identification. |  |



## Enum: IdentityEnum

| Name | Value |
|---- | -----|
| VERIFIED | &quot;verified&quot; |
| SIGNED | &quot;signed&quot; |
| SPOOFED | &quot;spoofed&quot; |
| UNKNOWN | &quot;unknown&quot; |



## Enum: ConfidenceEnum

| Name | Value |
|---- | -----|
| LOW | &quot;low&quot; |
| MEDIUM | &quot;medium&quot; |
| HIGH | &quot;high&quot; |



