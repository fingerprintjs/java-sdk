# FingerprintApi

All URIs are relative to *https://api.fpjs.io/v4*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**deleteVisitorData**](FingerprintApi.md#deleteVisitorData) | **DELETE** /visitors/{visitor_id} | Delete data by visitor ID |
| [**getEvent**](FingerprintApi.md#getEvent) | **GET** /events/{event_id} | Get an event by event ID |
| [**searchEvents**](FingerprintApi.md#searchEvents) | **GET** /events | Search events |
| [**updateEvent**](FingerprintApi.md#updateEvent) | **PATCH** /events/{event_id} | Update an event |



## deleteVisitorData

> deleteVisitorData(visitorId)

Delete data by visitor ID

Request deleting all data associated with the specified visitor ID. This API is useful for compliance with privacy regulations.

### Which data is deleted?
- Browser (or device) properties
- Identification requests made from this browser (or device)

#### Browser (or device) properties
- Represents the data that Fingerprint collected from this specific browser (or device) and everything inferred and derived from it.
- Upon request to delete, this data is deleted asynchronously (typically within a few minutes) and it will no longer be used to identify this browser (or device) for your [Fingerprint Workspace](https://docs.fingerprint.com/docs/glossary#fingerprint-workspace).

#### Identification requests made from this browser (or device)
- Fingerprint stores the identification requests made from a browser (or device) for up to 30 (or 90) days depending on your plan. To learn more, see [Data Retention](https://docs.fingerprint.com/docs/regions#data-retention).
- Upon request to delete, the identification requests that were made by this browser
  - Within the past 10 days are deleted within 24 hrs.
  - Outside of 10 days are allowed to purge as per your data retention period.

### Corollary
After requesting to delete a visitor ID,
- If the same browser (or device) requests to identify, it will receive a different visitor ID.
- If you request [`/v4/events` API](https://docs.fingerprint.com/reference/server-api-v4-get-event) with an `event_id` that was made outside of the 10 days, you will still receive a valid response.

### Interested?
Please [contact our support team](https://fingerprint.com/support/) to enable it for you. Otherwise, you will receive a 403.


### Example

```java
package main;

import java.util.*;

import com.fingerprint.v4.api.FingerprintApi;
import com.fingerprint.v4.model.*;
import com.fingerprint.v4.sdk.ApiClient;
import com.fingerprint.v4.sdk.ApiException;
import com.fingerprint.v4.sdk.Region;

public class FingerprintApiExample {
    // Fingerprint Secret API Key
    private static final String FPJS_API_SECRET = "Fingerprint Secret API Key";
    public static void main(String... args) {
        // Create a new api client instance from Configuration with your Fingerprint Server API Key and your Fingerprint Server API Region.
        /*
        You can specify a region on getDefaultApiClient function's second parameter
        If you leave the second parameter empty, then Region.GLOBAL will be used as a default region
        Options for regions are:
        Region.GLOBAL
        Region.EUROPE
        Region.ASIA
        */
        FingerprintApi api = new FingerprintApi(FPJS_API_SECRET, Region.EUROPE);
        String visitorId = "visitorId_example"; // String | The [visitor ID](https://docs.fingerprint.com/reference/js-agent-v4-get-function#visitor_id) you want to delete.
        try {
            api.deleteVisitorData(visitorId);
        } catch (ApiException e) {
            System.err.println("Exception when calling FingerprintApi.deleteVisitorData:" + e.getMessage());
        }
    }
}
```


### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **visitorId** | **String**| The [visitor ID](https://docs.fingerprint.com/reference/js-agent-v4-get-function#visitor_id) you want to delete. | |

### Return type

null (empty response body)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK. The visitor ID is scheduled for deletion. |  -  |
| **400** | Bad request. The visitor ID parameter is missing or in the wrong format. |  -  |
| **403** | Forbidden. Access to this API is denied. |  -  |
| **404** | Not found. The visitor ID cannot be found in this workspace's data. |  -  |
| **429** | Too Many Requests. The request is throttled. |  -  |


## getEvent

> Event getEvent(eventId, getEventOptionalParams)

Get an event by event ID

Get a detailed analysis of an individual identification event, including Smart Signals.

Use `event_id` as the URL path parameter. This API method is scoped to a request, i.e. all returned information is by `event_id`.


### Example

```java
package main;

import java.util.*;

import com.fingerprint.v4.api.FingerprintApi;
import com.fingerprint.v4.model.*;
import com.fingerprint.v4.sdk.ApiClient;
import com.fingerprint.v4.sdk.ApiException;
import com.fingerprint.v4.sdk.Region;

public class FingerprintApiExample {
    // Fingerprint Secret API Key
    private static final String FPJS_API_SECRET = "Fingerprint Secret API Key";
    public static void main(String... args) {
        // Create a new api client instance from Configuration with your Fingerprint Server API Key and your Fingerprint Server API Region.
        /*
        You can specify a region on getDefaultApiClient function's second parameter
        If you leave the second parameter empty, then Region.GLOBAL will be used as a default region
        Options for regions are:
        Region.GLOBAL
        Region.EUROPE
        Region.ASIA
        */
        FingerprintApi api = new FingerprintApi(FPJS_API_SECRET, Region.EUROPE);
        String eventId = "eventId_example"; // String | The unique [identifier](https://docs.fingerprint.com/reference/js-agent-v4-get-function#event_id) of each identification request (`requestId` can be used in its place).
        String rulesetId = "rulesetId_example"; // String | The ID of the ruleset to evaluate against the event, producing the action to take for this event. The resulting action is returned in the `rule_action` attribute of the response. 
        try {
            Event result = api.getEvent(eventId, new FingerprintApi.GetEventOptionalParams()
                .setRulesetId(rulesetId));
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling FingerprintApi.getEvent:" + e.getMessage());
        }
    }
}
```


### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **eventId** | **String**| The unique [identifier](https://docs.fingerprint.com/reference/js-agent-v4-get-function#event_id) of each identification request (`requestId` can be used in its place). | |
| **getEventOptionalParams** | [**FingerprintApi.GetEventOptionalParams**](#fingerprintapigeteventoptionalparams) | | [optional] |

#### FingerprintApi.GetEventOptionalParams

Object containing optional parameters for API method. Supports a fluent interface for convenient method chaining.

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **rulesetId** | **String**| The ID of the ruleset to evaluate against the event, producing the action to take for this event. The resulting action is returned in the `rule_action` attribute of the response.  | [optional] |

### Return type

[**Event**](Event.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK. |  -  |
| **400** | Bad request. The event Id provided is not valid. |  -  |
| **403** | Forbidden. Access to this API is denied. |  -  |
| **404** | Not found. The event Id cannot be found in this workspace's data. |  -  |
| **429** | Too Many Requests. The request is throttled. |  -  |
| **500** | Workspace error. |  -  |


## searchEvents

> EventSearch searchEvents(searchEventsOptionalParams)

Search events

## Search

The `/v4/events` endpoint provides a convenient way to search for past events based on specific parameters. Typical use cases and queries include:

- Searching for events associated with a single `visitor_id` within a time range to get historical behavior of a visitor.
- Searching for events associated with a single `linked_id` within a time range to get all events associated with your internal account identifier.
- Excluding all bot traffic from the query (`good` and `bad` bots)

By default, the API searches events from the last 7 days, sorts them by newest first and returns the last 10 events.

- Use `start` and `end` to specify the time range of the search.
- Use `reverse=true` to sort the results oldest first.
- Use `limit` to specify the number of events to return.
- Use `pagination_key` to get the next page of results if there are more than `limit` events.

### Filtering events with the `suspect` flag

The `/v4/events` endpoint unlocks a powerful method for fraud protection analytics. The `suspect` flag is exposed in all events where it was previously set by the update API.

You can also apply the `suspect` query parameter as a filter to find all potentially fraudulent activity that you previously marked as `suspect`. This helps identify patterns of fraudulent behavior.

### Environment scoping

If you use a secret key that is scoped to an environment, you will only get events associated with the same environment. With a workspace-scoped environment, you will get events from all environments.

Smart Signals not activated for your workspace or are not included in the response.


### Example

```java
package main;

import java.util.*;

import com.fingerprint.v4.api.FingerprintApi;
import com.fingerprint.v4.model.*;
import com.fingerprint.v4.sdk.ApiClient;
import com.fingerprint.v4.sdk.ApiException;
import com.fingerprint.v4.sdk.Region;

public class FingerprintApiExample {
    // Fingerprint Secret API Key
    private static final String FPJS_API_SECRET = "Fingerprint Secret API Key";
    public static void main(String... args) {
        // Create a new api client instance from Configuration with your Fingerprint Server API Key and your Fingerprint Server API Region.
        /*
        You can specify a region on getDefaultApiClient function's second parameter
        If you leave the second parameter empty, then Region.GLOBAL will be used as a default region
        Options for regions are:
        Region.GLOBAL
        Region.EUROPE
        Region.ASIA
        */
        FingerprintApi api = new FingerprintApi(FPJS_API_SECRET, Region.EUROPE);
        Integer limit = 10; // Integer | Maximum number of events to return. Results are selected from the time range (`start`, `end`), ordered by `reverse`, then truncated to provided `limit` size. So `reverse=true` returns the oldest N=`limit` events, otherwise the newest N=`limit` events. 
        String paginationKey = "paginationKey_example"; // String | Use `pagination_key` to get the next page of results.  When more results are available (e.g., you requested up to 100 results for your query using `limit`, but there are more than 100 events total matching your request), the `pagination_key` field is added to the response. The pagination key is an arbitrary string that should not be interpreted in any way and should be passed as-is. In the following request, use that value in the `pagination_key` parameter to get the next page of results:  1. First request, returning most recent 200 events: `GET api-base-url/events?limit=100` 2. Use `response.pagination_key` to get the next page of results: `GET api-base-url/events?limit=100&pagination_key=1740815825085` 
        String visitorId = "visitorId_example"; // String | Unique [visitor identifier](https://docs.fingerprint.com/reference/js-agent-v4-get-function#visitor_id) issued by Fingerprint Identification and all active Smart Signals.  Filter events by matching Visitor ID (`identification.visitor_id` property). 
        String highRecallId = "highRecallId_example"; // String | The High Recall ID is a supplementary browser identifier designed for use cases that require wider coverage over precision. Compared to the standard visitor ID, the High Recall ID strives to match incoming browsers more generously (rather than precisely) with existing browsers and thus identifies fewer browsers as new. The High Recall ID is best suited for use cases that are sensitive to browsers being identified as new and where mismatched browsers are not detrimental.  Filter events by matching High Recall ID (`supplementary_id_high_recall.visitor_id` property). 
        SearchEventsBot bot = SearchEventsBot.fromValue("all"); // SearchEventsBot | Filter events by the Bot Detection result, specifically:   `all` - events where any kind of bot was detected.   `good` - events where a good bot was detected.   `bad` - events where a bad bot was detected.   `none` - events where no bot was detected. > Note: When using this parameter, only events with the `bot` property set to a valid value are returned. Events without a `bot` Smart Signal result are left out of the response. 
        String ipAddress = "ipAddress_example"; // String | Filter events by IP address or IP range (if CIDR notation is used). If CIDR notation is not used, a /32 for IPv4 or /128 for IPv6 is assumed. Examples of range based queries: 10.0.0.0/24, 192.168.0.1/32 
        String asn = "asn_example"; // String | Filter events by the ASN associated with the event's IP address. This corresponds to the `ip_info.(v4|v6).asn` property in the response. 
        String linkedId = "linkedId_example"; // String | Filter events by your custom identifier.  You can use [linked Ids](https://docs.fingerprint.com/reference/js-agent-v4-get-function#linkedid) to associate identification requests with your own identifier, for example, session Id, purchase Id, or transaction Id. You can then use this `linked_id` parameter to retrieve all events associated with your custom identifier. 
        String url = "url_example"; // String | Filter events by the URL (`url` property) associated with the event. 
        String bundleId = "bundleId_example"; // String | Filter events by the Bundle ID (iOS) associated with the event. 
        String packageName = "packageName_example"; // String | Filter events by the Package Name (Android) associated with the event. 
        String origin = "origin_example"; // String | Filter events by the origin field of the event. This is applicable to web events only (e.g., https://example.com) 
        Long start = 1767225600000L; // Long | Include events that happened after this point (with timestamp greater than or equal the provided `start` Unix milliseconds value). Defaults to 7 days ago. Setting `start` does not change `end`'s default of `now` — adjust it separately if needed. 
        Long end = 1769903999000L; // Long | Include events that happened before this point (with timestamp less than or equal the provided `end` Unix milliseconds value). Defaults to now. Setting `end` does not change `start`'s default of `7 days ago` — adjust it separately if needed. 
        Boolean reverse = false; // Boolean | When `true`, sort events oldest first (ascending timestamp order). Default is newest first (descending timestamp order). 
        Boolean suspect = true; // Boolean | Filter events previously tagged as suspicious via the [Update API](https://docs.fingerprint.com/reference/server-api-v4-update-event). > Note: When using this parameter, only events with the `suspect` property explicitly set to `true` or `false` are returned. Events with undefined `suspect` property are left out of the response. 
        Boolean vpn = true; // Boolean | Filter events by VPN Detection result. > Note: When using this parameter, only events with the `vpn` property set to `true` or `false` are returned. Events without a `vpn` Smart Signal result are left out of the response. 
        Boolean virtualMachine = true; // Boolean | Filter events by Virtual Machine Detection result. > Note: When using this parameter, only events with the `virtual_machine` property set to `true` or `false` are returned. Events without a `virtual_machine` Smart Signal result are left out of the response. 
        Boolean tampering = true; // Boolean | Filter events by Browser Tampering Detection result. > Note: When using this parameter, only events with the `tampering.result` property set to `true` or `false` are returned. Events without a `tampering` Smart Signal result are left out of the response. 
        Boolean antiDetectBrowser = true; // Boolean | Filter events by Anti-detect Browser Detection result. > Note: When using this parameter, only events with the `tampering.anti_detect_browser` property set to `true` or `false` are returned. Events without a `tampering` Smart Signal result are left out of the response. 
        Boolean incognito = true; // Boolean | Filter events by Browser Incognito Detection result. > Note: When using this parameter, only events with the `incognito` property set to `true` or `false` are returned. Events without an `incognito` Smart Signal result are left out of the response. 
        Boolean privacySettings = true; // Boolean | Filter events by Privacy Settings Detection result. > Note: When using this parameter, only events with the `privacy_settings` property set to `true` or `false` are returned. Events without a `privacy_settings` Smart Signal result are left out of the response. 
        Boolean jailbroken = true; // Boolean | Filter events by Jailbroken Device Detection result. > Note: When using this parameter, only events with the `jailbroken` property set to `true` or `false` are returned. Events without a `jailbroken` Smart Signal result are left out of the response. 
        Boolean frida = true; // Boolean | Filter events by Frida Detection result. > Note: When using this parameter, only events with the `frida` property set to `true` or `false` are returned. Events without a `frida` Smart Signal result are left out of the response. 
        Boolean factoryReset = true; // Boolean | Filter events by Factory Reset Detection result. > Note: When using this parameter, only events with a `factory_reset` time. Events without a `factory_reset` Smart Signal result are left out of the response. 
        Boolean clonedApp = true; // Boolean | Filter events by Cloned App Detection result. > Note: When using this parameter, only events with the `cloned_app` property set to `true` or `false` are returned. Events without a `cloned_app` Smart Signal result are left out of the response. 
        Boolean emulator = true; // Boolean | Filter events by Android Emulator Detection result. > Note: When using this parameter, only events with the `emulator` property set to `true` or `false` are returned. Events without an `emulator` Smart Signal result are left out of the response. 
        Boolean rootApps = true; // Boolean | Filter events by Rooted Device Detection result. > Note: When using this parameter, only events with the `root_apps` property set to `true` or `false` are returned. Events without a `root_apps` Smart Signal result are left out of the response. 
        SearchEventsVpnConfidence vpnConfidence = SearchEventsVpnConfidence.fromValue("high"); // SearchEventsVpnConfidence | Filter events by VPN Detection result confidence level. `high` - events with high VPN Detection confidence. `medium` - events with medium VPN Detection confidence. `low` - events with low VPN Detection confidence. > Note: When using this parameter, only events with the `vpn.confidence` property set to a valid value are returned. Events without a `vpn` Smart Signal result are left out of the response. 
        Float minSuspectScore = 3.4F; // Float | Filter events with Suspect Score result above a provided minimum threshold. > Note: When using this parameter, only events where the `suspect_score` property set to a value exceeding your threshold are returned. Events without a `suspect_score` Smart Signal result are left out of the response. 
        Boolean developerTools = true; // Boolean | Filter events by Developer Tools detection result. > Note: When using this parameter, only events with the `developer_tools` property set to `true` or `false` are returned. Events without a `developer_tools` Smart Signal result are left out of the response. 
        Boolean locationSpoofing = true; // Boolean | Filter events by Location Spoofing detection result. > Note: When using this parameter, only events with the `location_spoofing` property set to `true` or `false` are returned. Events without a `location_spoofing` Smart Signal result are left out of the response. 
        Boolean mitmAttack = true; // Boolean | Filter events by MITM (Man-in-the-Middle) Attack detection result. > Note: When using this parameter, only events with the `mitm_attack` property set to `true` or `false` are returned. Events without a `mitm_attack` Smart Signal result are left out of the response. 
        Boolean rareDevice = true; // Boolean | Filter events by Device Rarity detection result. > Note: When using this parameter, only events with the `rare_device` property set to `true` or `false` are returned. Events without a Device Rarity Smart Signal result are left out of the response. 
        SearchEventsRareDevicePercentileBucket rareDevicePercentileBucket = SearchEventsRareDevicePercentileBucket.fromValue("<p95"); // SearchEventsRareDevicePercentileBucket | Filter events by Device Rarity percentile bucket. `<p95` - device configuration is in the bottom 95% (most common). `p95-p99` - device is in the 95th to 99th percentile. `p99-p99.5` - device is in the 99th to 99.5th percentile. `p99.5-p99.9` - device is in the 99.5th to 99.9th percentile. `p99.9+` - device is in the top 0.1% (rarest). `not_seen` - device configuration has never been observed before. 
        Boolean proxy = true; // Boolean | Filter events by Proxy detection result. > Note: When using this parameter, only events with the `proxy` property set to `true` or `false` are returned. Events without a `proxy` Smart Signal result are left out of the response. 
        String sdkVersion = "sdkVersion_example"; // String | Filter events by a specific SDK version associated with the identification event (`sdk.version` property). Example: `3.11.14` 
        SearchEventsSdkPlatform sdkPlatform = SearchEventsSdkPlatform.fromValue("js"); // SearchEventsSdkPlatform | Filter events by the SDK Platform associated with the identification event (`sdk.platform` property) . `js` - Javascript agent (Web). `ios` - Apple iOS based devices. `android` - Android based devices. 
        List<String> environment = Arrays.asList(); // List<String> | Filter for events by providing one or more environment IDs (`environment_id` property).  ### Array syntax To provide multiple environment IDs, use the repeated keys syntax (`environment=env1&environment=env2`). Other notations like comma-separated (`environment=env1,env2`) or bracket notation (`environment[]=env1&environment[]=env2`) are not supported. 
        String proximityId = "proximityId_example"; // String | Filter events by the most precise Proximity ID provided by default. > Note: When using this parameter, only events with the `proximity.id` property matching the provided ID are returned. Events without a `proximity` result are left out of the response. 
        Long totalHits = 56L; // Long | When set, the response will include a `total_hits` property with a count of total query matches across all pages, up to the specified limit. 
        Boolean torNode = true; // Boolean | Filter events by Tor Node detection result. > Note: When using this parameter, only events with the `tor_node` property set to `true` or `false` are returned. Events without a `tor_node` detection result are left out of the response. 
        SearchEventsIncrementalIdentificationStatus incrementalIdentificationStatus = SearchEventsIncrementalIdentificationStatus.fromValue("partially_completed"); // SearchEventsIncrementalIdentificationStatus | Filter events by their incremental identification status (`incremental_identification_status` property). Non incremental identification events are left out of the response. 
        Boolean simulator = true; // Boolean | Filter events by iOS Simulator Detection result.  > Note: When using this parameter, only events with the `simulator` property set to `true` or `false` are returned. Events without a `simulator` Smart Signal result are left out of the response. 
        try {
            EventSearch result = api.searchEvents(new FingerprintApi.SearchEventsOptionalParams()
                .setLimit(limit)
                .setPaginationKey(paginationKey)
                .setVisitorId(visitorId)
                .setHighRecallId(highRecallId)
                .setBot(bot)
                .setIpAddress(ipAddress)
                .setAsn(asn)
                .setLinkedId(linkedId)
                .setUrl(url)
                .setBundleId(bundleId)
                .setPackageName(packageName)
                .setOrigin(origin)
                .setStart(start)
                .setEnd(end)
                .setReverse(reverse)
                .setSuspect(suspect)
                .setVpn(vpn)
                .setVirtualMachine(virtualMachine)
                .setTampering(tampering)
                .setAntiDetectBrowser(antiDetectBrowser)
                .setIncognito(incognito)
                .setPrivacySettings(privacySettings)
                .setJailbroken(jailbroken)
                .setFrida(frida)
                .setFactoryReset(factoryReset)
                .setClonedApp(clonedApp)
                .setEmulator(emulator)
                .setRootApps(rootApps)
                .setVpnConfidence(vpnConfidence)
                .setMinSuspectScore(minSuspectScore)
                .setDeveloperTools(developerTools)
                .setLocationSpoofing(locationSpoofing)
                .setMitmAttack(mitmAttack)
                .setRareDevice(rareDevice)
                .setRareDevicePercentileBucket(rareDevicePercentileBucket)
                .setProxy(proxy)
                .setSdkVersion(sdkVersion)
                .setSdkPlatform(sdkPlatform)
                .setEnvironment(environment)
                .setProximityId(proximityId)
                .setTotalHits(totalHits)
                .setTorNode(torNode)
                .setIncrementalIdentificationStatus(incrementalIdentificationStatus)
                .setSimulator(simulator));
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling FingerprintApi.searchEvents:" + e.getMessage());
        }
    }
}
```


### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **searchEventsOptionalParams** | [**FingerprintApi.SearchEventsOptionalParams**](#fingerprintapisearcheventsoptionalparams) | | [optional] |

#### FingerprintApi.SearchEventsOptionalParams

Object containing optional parameters for API method. Supports a fluent interface for convenient method chaining.

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **limit** | **Integer**| Maximum number of events to return. Results are selected from the time range (`start`, `end`), ordered by `reverse`, then truncated to provided `limit` size. So `reverse=true` returns the oldest N=`limit` events, otherwise the newest N=`limit` events.  | [optional] [default to 10] |
| **paginationKey** | **String**| Use `pagination_key` to get the next page of results.  When more results are available (e.g., you requested up to 100 results for your query using `limit`, but there are more than 100 events total matching your request), the `pagination_key` field is added to the response. The pagination key is an arbitrary string that should not be interpreted in any way and should be passed as-is. In the following request, use that value in the `pagination_key` parameter to get the next page of results:  1. First request, returning most recent 200 events: `GET api-base-url/events?limit=100` 2. Use `response.pagination_key` to get the next page of results: `GET api-base-url/events?limit=100&pagination_key=1740815825085`  | [optional] |
| **visitorId** | **String**| Unique [visitor identifier](https://docs.fingerprint.com/reference/js-agent-v4-get-function#visitor_id) issued by Fingerprint Identification and all active Smart Signals.  Filter events by matching Visitor ID (`identification.visitor_id` property).  | [optional] |
| **highRecallId** | **String**| The High Recall ID is a supplementary browser identifier designed for use cases that require wider coverage over precision. Compared to the standard visitor ID, the High Recall ID strives to match incoming browsers more generously (rather than precisely) with existing browsers and thus identifies fewer browsers as new. The High Recall ID is best suited for use cases that are sensitive to browsers being identified as new and where mismatched browsers are not detrimental.  Filter events by matching High Recall ID (`supplementary_id_high_recall.visitor_id` property).  | [optional] |
| **bot** | **SearchEventsBot**| Filter events by the Bot Detection result, specifically:   `all` - events where any kind of bot was detected.   `good` - events where a good bot was detected.   `bad` - events where a bad bot was detected.   `none` - events where no bot was detected. > Note: When using this parameter, only events with the `bot` property set to a valid value are returned. Events without a `bot` Smart Signal result are left out of the response.  | [optional] [enum: all, good, bad, none] |
| **ipAddress** | **String**| Filter events by IP address or IP range (if CIDR notation is used). If CIDR notation is not used, a /32 for IPv4 or /128 for IPv6 is assumed. Examples of range based queries: 10.0.0.0/24, 192.168.0.1/32  | [optional] |
| **asn** | **String**| Filter events by the ASN associated with the event's IP address. This corresponds to the `ip_info.(v4|v6).asn` property in the response.  | [optional] |
| **linkedId** | **String**| Filter events by your custom identifier.  You can use [linked Ids](https://docs.fingerprint.com/reference/js-agent-v4-get-function#linkedid) to associate identification requests with your own identifier, for example, session Id, purchase Id, or transaction Id. You can then use this `linked_id` parameter to retrieve all events associated with your custom identifier.  | [optional] |
| **url** | **String**| Filter events by the URL (`url` property) associated with the event.  | [optional] |
| **bundleId** | **String**| Filter events by the Bundle ID (iOS) associated with the event.  | [optional] |
| **packageName** | **String**| Filter events by the Package Name (Android) associated with the event.  | [optional] |
| **origin** | **String**| Filter events by the origin field of the event. This is applicable to web events only (e.g., https://example.com)  | [optional] |
| **start** | **Long**| Include events that happened after this point (with timestamp greater than or equal the provided `start` Unix milliseconds value). Defaults to 7 days ago. Setting `start` does not change `end`'s default of `now` — adjust it separately if needed.  | [optional] |
| **end** | **Long**| Include events that happened before this point (with timestamp less than or equal the provided `end` Unix milliseconds value). Defaults to now. Setting `end` does not change `start`'s default of `7 days ago` — adjust it separately if needed.  | [optional] |
| **reverse** | **Boolean**| When `true`, sort events oldest first (ascending timestamp order). Default is newest first (descending timestamp order).  | [optional] [default to false] |
| **suspect** | **Boolean**| Filter events previously tagged as suspicious via the [Update API](https://docs.fingerprint.com/reference/server-api-v4-update-event). > Note: When using this parameter, only events with the `suspect` property explicitly set to `true` or `false` are returned. Events with undefined `suspect` property are left out of the response.  | [optional] |
| **vpn** | **Boolean**| Filter events by VPN Detection result. > Note: When using this parameter, only events with the `vpn` property set to `true` or `false` are returned. Events without a `vpn` Smart Signal result are left out of the response.  | [optional] |
| **virtualMachine** | **Boolean**| Filter events by Virtual Machine Detection result. > Note: When using this parameter, only events with the `virtual_machine` property set to `true` or `false` are returned. Events without a `virtual_machine` Smart Signal result are left out of the response.  | [optional] |
| **tampering** | **Boolean**| Filter events by Browser Tampering Detection result. > Note: When using this parameter, only events with the `tampering.result` property set to `true` or `false` are returned. Events without a `tampering` Smart Signal result are left out of the response.  | [optional] |
| **antiDetectBrowser** | **Boolean**| Filter events by Anti-detect Browser Detection result. > Note: When using this parameter, only events with the `tampering.anti_detect_browser` property set to `true` or `false` are returned. Events without a `tampering` Smart Signal result are left out of the response.  | [optional] |
| **incognito** | **Boolean**| Filter events by Browser Incognito Detection result. > Note: When using this parameter, only events with the `incognito` property set to `true` or `false` are returned. Events without an `incognito` Smart Signal result are left out of the response.  | [optional] |
| **privacySettings** | **Boolean**| Filter events by Privacy Settings Detection result. > Note: When using this parameter, only events with the `privacy_settings` property set to `true` or `false` are returned. Events without a `privacy_settings` Smart Signal result are left out of the response.  | [optional] |
| **jailbroken** | **Boolean**| Filter events by Jailbroken Device Detection result. > Note: When using this parameter, only events with the `jailbroken` property set to `true` or `false` are returned. Events without a `jailbroken` Smart Signal result are left out of the response.  | [optional] |
| **frida** | **Boolean**| Filter events by Frida Detection result. > Note: When using this parameter, only events with the `frida` property set to `true` or `false` are returned. Events without a `frida` Smart Signal result are left out of the response.  | [optional] |
| **factoryReset** | **Boolean**| Filter events by Factory Reset Detection result. > Note: When using this parameter, only events with a `factory_reset` time. Events without a `factory_reset` Smart Signal result are left out of the response.  | [optional] |
| **clonedApp** | **Boolean**| Filter events by Cloned App Detection result. > Note: When using this parameter, only events with the `cloned_app` property set to `true` or `false` are returned. Events without a `cloned_app` Smart Signal result are left out of the response.  | [optional] |
| **emulator** | **Boolean**| Filter events by Android Emulator Detection result. > Note: When using this parameter, only events with the `emulator` property set to `true` or `false` are returned. Events without an `emulator` Smart Signal result are left out of the response.  | [optional] |
| **rootApps** | **Boolean**| Filter events by Rooted Device Detection result. > Note: When using this parameter, only events with the `root_apps` property set to `true` or `false` are returned. Events without a `root_apps` Smart Signal result are left out of the response.  | [optional] |
| **vpnConfidence** | **SearchEventsVpnConfidence**| Filter events by VPN Detection result confidence level. `high` - events with high VPN Detection confidence. `medium` - events with medium VPN Detection confidence. `low` - events with low VPN Detection confidence. > Note: When using this parameter, only events with the `vpn.confidence` property set to a valid value are returned. Events without a `vpn` Smart Signal result are left out of the response.  | [optional] [enum: high, medium, low] |
| **minSuspectScore** | **Float**| Filter events with Suspect Score result above a provided minimum threshold. > Note: When using this parameter, only events where the `suspect_score` property set to a value exceeding your threshold are returned. Events without a `suspect_score` Smart Signal result are left out of the response.  | [optional] |
| **developerTools** | **Boolean**| Filter events by Developer Tools detection result. > Note: When using this parameter, only events with the `developer_tools` property set to `true` or `false` are returned. Events without a `developer_tools` Smart Signal result are left out of the response.  | [optional] |
| **locationSpoofing** | **Boolean**| Filter events by Location Spoofing detection result. > Note: When using this parameter, only events with the `location_spoofing` property set to `true` or `false` are returned. Events without a `location_spoofing` Smart Signal result are left out of the response.  | [optional] |
| **mitmAttack** | **Boolean**| Filter events by MITM (Man-in-the-Middle) Attack detection result. > Note: When using this parameter, only events with the `mitm_attack` property set to `true` or `false` are returned. Events without a `mitm_attack` Smart Signal result are left out of the response.  | [optional] |
| **rareDevice** | **Boolean**| Filter events by Device Rarity detection result. > Note: When using this parameter, only events with the `rare_device` property set to `true` or `false` are returned. Events without a Device Rarity Smart Signal result are left out of the response.  | [optional] |
| **rareDevicePercentileBucket** | **SearchEventsRareDevicePercentileBucket**| Filter events by Device Rarity percentile bucket. `<p95` - device configuration is in the bottom 95% (most common). `p95-p99` - device is in the 95th to 99th percentile. `p99-p99.5` - device is in the 99th to 99.5th percentile. `p99.5-p99.9` - device is in the 99.5th to 99.9th percentile. `p99.9+` - device is in the top 0.1% (rarest). `not_seen` - device configuration has never been observed before.  | [optional] [enum: <p95, p95-p99, p99-p99.5, p99.5-p99.9, p99.9+, not_seen] |
| **proxy** | **Boolean**| Filter events by Proxy detection result. > Note: When using this parameter, only events with the `proxy` property set to `true` or `false` are returned. Events without a `proxy` Smart Signal result are left out of the response.  | [optional] |
| **sdkVersion** | **String**| Filter events by a specific SDK version associated with the identification event (`sdk.version` property). Example: `3.11.14`  | [optional] |
| **sdkPlatform** | **SearchEventsSdkPlatform**| Filter events by the SDK Platform associated with the identification event (`sdk.platform` property) . `js` - Javascript agent (Web). `ios` - Apple iOS based devices. `android` - Android based devices.  | [optional] [enum: js, android, ios] |
| **environment** | **List&lt;String&gt;**| Filter for events by providing one or more environment IDs (`environment_id` property).  ### Array syntax To provide multiple environment IDs, use the repeated keys syntax (`environment=env1&environment=env2`). Other notations like comma-separated (`environment=env1,env2`) or bracket notation (`environment[]=env1&environment[]=env2`) are not supported.  | [optional] |
| **proximityId** | **String**| Filter events by the most precise Proximity ID provided by default. > Note: When using this parameter, only events with the `proximity.id` property matching the provided ID are returned. Events without a `proximity` result are left out of the response.  | [optional] |
| **totalHits** | **Long**| When set, the response will include a `total_hits` property with a count of total query matches across all pages, up to the specified limit.  | [optional] |
| **torNode** | **Boolean**| Filter events by Tor Node detection result. > Note: When using this parameter, only events with the `tor_node` property set to `true` or `false` are returned. Events without a `tor_node` detection result are left out of the response.  | [optional] |
| **incrementalIdentificationStatus** | **SearchEventsIncrementalIdentificationStatus**| Filter events by their incremental identification status (`incremental_identification_status` property). Non incremental identification events are left out of the response.  | [optional] [enum: partially_completed, completed] |
| **simulator** | **Boolean**| Filter events by iOS Simulator Detection result.  > Note: When using this parameter, only events with the `simulator` property set to `true` or `false` are returned. Events without a `simulator` Smart Signal result are left out of the response.  | [optional] |

### Return type

[**EventSearch**](EventSearch.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Events matching the filter(s). |  -  |
| **400** | Bad request. One or more supplied search parameters are invalid, or a required parameter is missing. |  -  |
| **403** | Forbidden. Access to this API is denied. |  -  |
| **500** | Workspace error. |  -  |


## updateEvent

> updateEvent(eventId, eventUpdate)

Update an event

Change information in existing events specified by `event_id` or *flag suspicious events*.

When an event is created, it can be assigned `linked_id` and `tags` submitted through the JS agent parameters. 
This information might not have been available on the client initially, so the Server API permits updating these attributes after the fact.

**Warning** It's not possible to update events older than one month. 

**Warning** Trying to update an event immediately after creation may temporarily result in an 
error (HTTP 409 Conflict. The event is not mutable yet.) as the event is fully propagated across our systems. In such a case, simply retry the request.


### Example

```java
package main;

import java.util.*;

import com.fingerprint.v4.api.FingerprintApi;
import com.fingerprint.v4.model.*;
import com.fingerprint.v4.sdk.ApiClient;
import com.fingerprint.v4.sdk.ApiException;
import com.fingerprint.v4.sdk.Region;

public class FingerprintApiExample {
    // Fingerprint Secret API Key
    private static final String FPJS_API_SECRET = "Fingerprint Secret API Key";
    public static void main(String... args) {
        // Create a new api client instance from Configuration with your Fingerprint Server API Key and your Fingerprint Server API Region.
        /*
        You can specify a region on getDefaultApiClient function's second parameter
        If you leave the second parameter empty, then Region.GLOBAL will be used as a default region
        Options for regions are:
        Region.GLOBAL
        Region.EUROPE
        Region.ASIA
        */
        FingerprintApi api = new FingerprintApi(FPJS_API_SECRET, Region.EUROPE);
        String eventId = "eventId_example"; // String | The unique event [identifier](https://docs.fingerprint.com/reference/js-agent-v4-get-function#event_id).
        EventUpdate eventUpdate = new EventUpdate(); // EventUpdate | 
        try {
            api.updateEvent(eventId, eventUpdate);
        } catch (ApiException e) {
            System.err.println("Exception when calling FingerprintApi.updateEvent:" + e.getMessage());
        }
    }
}
```


### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **eventId** | **String**| The unique event [identifier](https://docs.fingerprint.com/reference/js-agent-v4-get-function#event_id). | |
| **eventUpdate** | [**EventUpdate**](EventUpdate.md)|  | |

### Return type

null (empty response body)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK. |  -  |
| **400** | Bad request. The request payload is not valid. |  -  |
| **403** | Forbidden. Access to this API is denied. |  -  |
| **404** | Not found. The event Id cannot be found in this workspace's data. |  -  |
| **409** | Conflict. The event is not mutable yet. |  -  |

