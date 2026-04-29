

# SearchEventsRareDevicePercentileBucket
Filter events by Device Rarity percentile bucket.
`<p95` - device configuration is in the bottom 95% (most common).
`p95-p99` - device is in the 95th to 99th percentile.
`p99-p99.5` - device is in the 99th to 99.5th percentile.
`p99.5-p99.9` - device is in the 99.5th to 99.9th percentile.
`p99.9+` - device is in the top 0.1% (rarest).
`not_seen` - device configuration has never been observed before.


## Enum


* `_P95` (value: `"<p95"`)

* `P95_P99` (value: `"p95-p99"`)

* `P99_P99_5` (value: `"p99-p99.5"`)

* `P99_5_P99_9` (value: `"p99.5-p99.9"`)

* `P99_9_` (value: `"p99.9+"`)

* `NOT_SEEN` (value: `"not_seen"`)

* `UNSUPPORTED_VALUE_SDK_UPGRADE_REQUIRED` (value: `"unsupported_value_sdk_upgrade_required"`)



