

# Event

Contains results from Fingerprint Identification and all active Smart Signals.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**eventId** | **String** | Unique identifier of the user's request. The first portion of the event_id is a unix epoch milliseconds timestamp For example: `1758130560902.8tRtrH`  |  |
|**timestamp** | **Long** | Timestamp of the event with millisecond precision in Unix time. |  |
|**linkedId** | **String** | A customer-provided id that was sent with the request. |  [optional] |
|**environmentId** | **String** | Environment Id of the event. For example: `ae_47abaca3db2c7c43`  |  [optional] |
|**suspect** | **Boolean** | Field is `true` if you have previously set the `suspect` flag for this event using the [Server API Update event endpoint](https://dev.fingerprint.com/reference/updateevent). |  [optional] |
|**sdk** | [**SDK**](SDK.md) |  |  [optional] |
|**replayed** | **Boolean** | `true` if we determined that this payload was replayed, `false` otherwise.  |  [optional] |
|**identification** | [**Identification**](Identification.md) |  |  [optional] |
|**supplementaryIdHighRecall** | [**SupplementaryIDHighRecall**](SupplementaryIDHighRecall.md) |  |  [optional] |
|**tags** | **Object** | A customer-provided value or an object that was sent with the identification request or updated later. |  [optional] |
|**url** | **String** | Page URL from which the request was sent. For example `https://example.com/`  |  [optional] |
|**bundleId** | **String** | Bundle Id of the iOS application integrated with the Fingerprint SDK for the event. For example: `com.foo.app`  |  [optional] |
|**packageName** | **String** | Package name of the Android application integrated with the Fingerprint SDK for the event. For example: `com.foo.app`  |  [optional] |
|**ipAddress** | **String** | IP address of the requesting browser or bot. |  [optional] |
|**userAgent** | **String** | User Agent of the client, for example: `Mozilla/5.0 (Windows NT 6.1; Win64; x64) ....`  |  [optional] |
|**clientReferrer** | **String** | Client Referrer field corresponds to the `document.referrer` field gathered during an identification request. The value is an empty string if the user navigated to the page directly (not through a link, but, for example, by using a bookmark) For example: `https://example.com/blog/my-article`  |  [optional] |
|**browserDetails** | [**BrowserDetails**](BrowserDetails.md) |  |  [optional] |
|**proximity** | [**Proximity**](Proximity.md) |  |  [optional] |
|**bot** | **BotResult** |  |  [optional] |
|**botType** | **String** | Additional classification of the bot type if detected.  |  [optional] |
|**botInfo** | [**BotInfo**](BotInfo.md) |  |  [optional] |
|**clonedApp** | **Boolean** | Android specific cloned application detection. There are 2 values:  * `true` - Presence of app cloners work detected (e.g. fully cloned application found or launch of it inside of a not main working profile detected). * `false` - No signs of cloned application detected or the client is not Android.  |  [optional] |
|**developerTools** | **Boolean** | `true` if the browser is Chrome with DevTools open or Firefox with Developer Tools open, `false` otherwise.  |  [optional] |
|**emulator** | **Boolean** | Android specific emulator detection. There are 2 values:  * `true` - Emulated environment detected (e.g. launch inside of AVD).  * `false` - No signs of emulated environment detected or the client is not Android.  |  [optional] |
|**factoryResetTimestamp** | **Long** | The time of the most recent factory reset that happened on the **mobile device** is expressed as Unix epoch time. When a factory reset cannot be detected on the mobile device or when the request is initiated from a browser,  this field will correspond to the *epoch* time (i.e 1 Jan 1970 UTC) as a value of 0. See [Factory Reset Detection](https://dev.fingerprint.com/docs/smart-signals-overview#factory-reset-detection) to learn more about this Smart Signal.  |  [optional] |
|**frida** | **Boolean** | [Frida](https://frida.re/docs/) detection for Android and iOS devices. There are 2 values: * `true` - Frida detected * `false` - No signs of Frida or the client is not a mobile device.  |  [optional] |
|**ipBlocklist** | [**IPBlockList**](IPBlockList.md) |  |  [optional] |
|**ipInfo** | [**IPInfo**](IPInfo.md) |  |  [optional] |
|**proxy** | **Boolean** | IP address was used by a public proxy provider or belonged to a known recent residential proxy  |  [optional] |
|**proxyConfidence** | **ProxyConfidence** |  |  [optional] |
|**proxyDetails** | [**ProxyDetails**](ProxyDetails.md) |  |  [optional] |
|**incognito** | **Boolean** | `true` if we detected incognito mode used in the browser, `false` otherwise.  |  [optional] |
|**jailbroken** | **Boolean** | iOS specific jailbreak detection. There are 2 values:  * `true` - Jailbreak detected. * `false` - No signs of jailbreak or the client is not iOS.  |  [optional] |
|**locationSpoofing** | **Boolean** | Flag indicating whether the request came from a mobile device with location spoofing enabled. |  [optional] |
|**mitmAttack** | **Boolean** | * `true` - When requests made from your users' mobile devices to Fingerprint servers have been intercepted and potentially modified.  * `false` - Otherwise or when the request originated from a browser. See [MitM Attack Detection](https://dev.fingerprint.com/docs/smart-signals-reference#mitm-attack-detection) to learn more about this Smart Signal.  |  [optional] |
|**privacySettings** | **Boolean** | `true` if the request is from a privacy aware browser (e.g. Tor) or from a browser in which fingerprinting is blocked. Otherwise `false`.  |  [optional] |
|**rootApps** | **Boolean** | Android specific root management apps detection. There are 2 values:  * `true` - Root Management Apps detected (e.g. Magisk). * `false` - No Root Management Apps detected or the client isn't Android.  |  [optional] |
|**ruleAction** | [**EventRuleAction**](EventRuleAction.md) |  |  [optional] |
|**suspectScore** | **Integer** | Suspect Score is an easy way to integrate Smart Signals into your fraud protection work flow.  It is a weighted representation of all Smart Signals present in the payload that helps identify suspicious activity. The value range is [0; S] where S is sum of all Smart Signals weights.  See more details here: https://dev.fingerprint.com/docs/suspect-score  |  [optional] |
|**tampering** | **Boolean** | Flag indicating browser tampering was detected. This happens when either:   * There are inconsistencies in the browser configuration that cross internal tampering thresholds (see `tampering_details.anomaly_score`).   * The browser signature resembles an \"anti-detect\" browser specifically designed to evade fingerprinting (see `tampering_details.anti_detect_browser`).  |  [optional] |
|**tamperingDetails** | [**TamperingDetails**](TamperingDetails.md) |  |  [optional] |
|**velocity** | [**Velocity**](Velocity.md) |  |  [optional] |
|**virtualMachine** | **Boolean** | `true` if the request came from a browser running inside a virtual machine (e.g. VMWare), `false` otherwise.  |  [optional] |
|**vpn** | **Boolean** | VPN or other anonymizing service has been used when sending the request.  |  [optional] |
|**vpnConfidence** | **VpnConfidence** |  |  [optional] |
|**vpnOriginTimezone** | **String** | Local timezone which is used in timezone_mismatch method.  |  [optional] |
|**vpnOriginCountry** | **String** | Country of the request (only for Android SDK version >= 2.4.0, ISO 3166 format or unknown).  |  [optional] |
|**vpnMethods** | [**VpnMethods**](VpnMethods.md) |  |  [optional] |
|**highActivityDevice** | **Boolean** | Flag indicating if the request came from a high-activity visitor. |  [optional] |
|**rawDeviceAttributes** | [**RawDeviceAttributes**](RawDeviceAttributes.md) |  |  [optional] |



