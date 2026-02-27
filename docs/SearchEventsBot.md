

# SearchEventsBot
Filter events by the Bot Detection result, specifically:
  `all` - events where any kind of bot was detected.
  `good` - events where a good bot was detected.
  `bad` - events where a bad bot was detected.
  `none` - events where no bot was detected.
> Note: When using this parameter, only events with the `bot` property set to a valid value are returned. Events without a `bot` Smart Signal result are left out of the response.


## Enum


* `ALL` (value: `"all"`)

* `GOOD` (value: `"good"`)

* `BAD` (value: `"bad"`)

* `NONE` (value: `"none"`)

* `UNSUPPORTED_VALUE_SDK_UPGRADE_REQUIRED` (value: `"unsupported_value_sdk_upgrade_required"`)



