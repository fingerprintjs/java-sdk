

# RawDeviceAttributes

A curated subset of raw browser/device attributes that the API surface exposes. Each property contains a value or object with the data for the collected signal. 

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**fontPreferences** | [**FontPreferences**](FontPreferences.md) |  |  [optional] |
|**emoji** | [**Emoji**](Emoji.md) |  |  [optional] |
|**fonts** | **List&lt;String&gt;** | List of fonts detected on the device. |  [optional] |
|**deviceMemory** | **Integer** | Rounded amount of RAM (in gigabytes) reported by the browser. |  [optional] |
|**timezone** | **String** | Timezone identifier detected on the client. |  [optional] |
|**canvas** | [**Canvas**](Canvas.md) |  |  [optional] |
|**languages** | **List&lt;List&lt;String&gt;&gt;** | Navigator languages reported by the agent including fallbacks. Each inner array represents ordered language preferences reported by different APIs.  |  [optional] |
|**webglExtensions** | [**WebGlExtensions**](WebGlExtensions.md) |  |  [optional] |
|**webglBasics** | [**WebGlBasics**](WebGlBasics.md) |  |  [optional] |
|**screenResolution** | **List&lt;Integer&gt;** | Current screen resolution. |  [optional] |
|**touchSupport** | [**TouchSupport**](TouchSupport.md) |  |  [optional] |
|**oscpu** | **String** | Navigator `oscpu` string. |  [optional] |
|**architecture** | **Integer** | Integer representing the CPU architecture exposed by the browser. |  [optional] |
|**cookiesEnabled** | **Boolean** | Whether the cookies are enabled in the browser. |  [optional] |
|**hardwareConcurrency** | **Integer** | Number of logical CPU cores reported by the browser. |  [optional] |
|**dateTimeLocale** | **String** | Locale derived from the Intl.DateTimeFormat API. Negative values indicate known error states. The negative statuses can be: - \"-1\": A permanent status for browsers that don't support Intl API. - \"-2\": A permanent status for browsers that don't supportDateTimeFormat constructor. - \"-3\": A permanent status for browsers in which DateTimeFormat locale is undefined or null.  |  [optional] |
|**vendor** | **String** | Navigator vendor string. |  [optional] |
|**colorDepth** | **Integer** | Screen color depth in bits. |  [optional] |
|**platform** | **String** | Navigator platform string. |  [optional] |
|**sessionStorage** | **Boolean** | Whether sessionStorage is available. |  [optional] |
|**localStorage** | **Boolean** | Whether localStorage is available. |  [optional] |
|**audio** | **Double** | AudioContext fingerprint or negative status when unavailable. The negative statuses can be: - -1: A permanent status for those browsers which are known to always suspend audio context - -2: A permanent status for browsers that don't support the signal - -3: A temporary status that means that an unexpected timeout has happened  |  [optional] |
|**plugins** | [**List&lt;PluginsInner&gt;**](PluginsInner.md) | Browser plugins reported by `navigator.plugins`. |  [optional] |
|**indexedDb** | **Boolean** | Whether IndexedDB is available. |  [optional] |
|**math** | **String** | Hash of Math APIs used for entropy collection. |  [optional] |



