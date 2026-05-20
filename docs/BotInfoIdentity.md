

# BotInfoIdentity
The verification status of the bot's identity:
 * `verified` - well-known bot with publicly verifiable identity, directed by the bot provider.
 * `signed` - bot that signs its platform via Web Bot Auth, directed by the bot provider's customers.
 * `spoofed` - bot that claims a public identity but fails verification.
 * `unknown` - bot that does not publish a verifiable identity.


## Enum


* `VERIFIED` (value: `"verified"`)

* `SIGNED` (value: `"signed"`)

* `SPOOFED` (value: `"spoofed"`)

* `UNKNOWN` (value: `"unknown"`)

* `UNSUPPORTED_VALUE_SDK_UPGRADE_REQUIRED` (value: `"unsupported_value_sdk_upgrade_required"`)



