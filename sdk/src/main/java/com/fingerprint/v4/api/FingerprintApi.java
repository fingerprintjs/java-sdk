package com.fingerprint.v4.api;

import com.fingerprint.v4.model.BotInfoCategory;
import com.fingerprint.v4.model.BotInfoConfidence;
import com.fingerprint.v4.model.BotInfoIdentity;
import com.fingerprint.v4.model.Event;
import com.fingerprint.v4.model.EventSearch;
import com.fingerprint.v4.model.EventUpdate;
import com.fingerprint.v4.model.SearchEventsBot;
import com.fingerprint.v4.model.SearchEventsBotInfo;
import com.fingerprint.v4.model.SearchEventsIncrementalIdentificationStatus;
import com.fingerprint.v4.model.SearchEventsRareDevicePercentileBucket;
import com.fingerprint.v4.model.SearchEventsSdkPlatform;
import com.fingerprint.v4.model.SearchEventsSource;
import com.fingerprint.v4.model.SearchEventsVpnConfidence;
import com.fingerprint.v4.sdk.ApiClient;
import com.fingerprint.v4.sdk.ApiException;
import com.fingerprint.v4.sdk.ApiResponse;
import com.fingerprint.v4.sdk.Configuration;
import com.fingerprint.v4.sdk.Pair;
import com.fingerprint.v4.sdk.Region;
import jakarta.ws.rs.core.GenericType;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@jakarta.annotation.Generated(
    value = "org.openapitools.codegen.languages.JavaClientCodegen",
    comments = "Generator version: 7.16.0")
public class FingerprintApi {
  public static final String INTEGRATION_INFO = "fingerprint-pro-server-java-sdk/8.3.0";
  private ApiClient apiClient;

  public FingerprintApi() {
    this(Configuration.getDefaultApiClient());
  }

  public FingerprintApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public FingerprintApi(String apiKey) {
    this(Configuration.getDefaultApiClient(apiKey));
  }

  public FingerprintApi(String apiKey, Region region) {
    this(Configuration.getDefaultApiClient(apiKey, region));
  }

  /**
   * Get the API client
   *
   * @return API client
   */
  public ApiClient getApiClient() {
    return apiClient;
  }

  /**
   * Set the API client
   *
   * @param apiClient an instance of API client
   */
  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * Delete a visitor ID
   * Use this API to request the deletion of all data associated with a specific visitor ID.  Upon a request to delete data for a visitor ID, - The data collected from the corresponding browser (or device) will be deleted asynchronously, typically within a few minutes. This data will no longer be available to identify this browser (or device). When the same browser (or device) revisits, it will receive a new visitor ID. - The identification events made from this browser (or device) in the past 10 days are typically deleted within 24 hrs.  - The identification events made from this browser (or device) outside of the 10 days will be purged as per your [data retention period](https://docs.fingerprint.com/docs/regions#data-retention).  The following timeline illustrates which events are deleted and which remain after a DELETE API request: ``` Day 1:  First visit from browser A. (Assigned visitor ID: VID1000) Day 2:  Browser A revisits. (Assigned the same visitor ID: VID1000) Day 13: Browser A revisits. (Assigned the same visitor ID: VID1000) Day 14: Delete VID1000 Day 15: Browser A re-visits. (Assigned a different visitor ID: VID9999) Day 15: GET /events/day-13 (Returns 404. The event is within the 10 days of deleting VID1000 and will have been deleted) Day 16: GET /events/day-2 (Returns 200. The event is outside of the 10 days of deleting VID1000 and is still available) ```  ### Availability This API is available only for Enterprise plans **upon request**. If you are interested, please [contact our support team](https://fingerprint.com/support/).  ### Rate limits and daily quota The rate limits and daily quota for this API **differ** from those for our other API.  The maximum number of DELETE requests that can be made in an hour cannot exceed 30 RPH, and the maximum number that can be made in a day cannot exceed 500 RPD.  You can request an increase to these limits by contacting [our support team](https://fingerprint.com/support/).
   * @param visitorId The [visitor ID](https://docs.fingerprint.com/reference/js-agent-v4-get-function#visitor_id) you want to delete. (required)
   * @throws ApiException if fails to make API call
   * @http.response.details
   * <table summary="Response Details" border="1">
   * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
   * <tr><td> 200 </td><td> OK. The visitor ID is scheduled for deletion. </td><td>  -  </td></tr>
   * <tr><td> 400 </td><td> Bad request. The visitor ID parameter is missing or in the wrong format. </td><td>  -  </td></tr>
   * <tr><td> 403 </td><td> Forbidden. Access to this API is denied. </td><td>  -  </td></tr>
   * <tr><td> 404 </td><td> Not found. The visitor ID cannot be found in this workspace&#39;s data. </td><td>  -  </td></tr>
   * <tr><td> 429 </td><td> Too Many Requests. The request is throttled. </td><td>  -  </td></tr>
   * </table>
   */
  public void deleteVisitorData(String visitorId) throws ApiException {
    deleteVisitorDataWithHttpInfo(visitorId);
  }

  /**
   * Delete a visitor ID
   * Use this API to request the deletion of all data associated with a specific visitor ID.  Upon a request to delete data for a visitor ID, - The data collected from the corresponding browser (or device) will be deleted asynchronously, typically within a few minutes. This data will no longer be available to identify this browser (or device). When the same browser (or device) revisits, it will receive a new visitor ID. - The identification events made from this browser (or device) in the past 10 days are typically deleted within 24 hrs.  - The identification events made from this browser (or device) outside of the 10 days will be purged as per your [data retention period](https://docs.fingerprint.com/docs/regions#data-retention).  The following timeline illustrates which events are deleted and which remain after a DELETE API request: ``` Day 1:  First visit from browser A. (Assigned visitor ID: VID1000) Day 2:  Browser A revisits. (Assigned the same visitor ID: VID1000) Day 13: Browser A revisits. (Assigned the same visitor ID: VID1000) Day 14: Delete VID1000 Day 15: Browser A re-visits. (Assigned a different visitor ID: VID9999) Day 15: GET /events/day-13 (Returns 404. The event is within the 10 days of deleting VID1000 and will have been deleted) Day 16: GET /events/day-2 (Returns 200. The event is outside of the 10 days of deleting VID1000 and is still available) ```  ### Availability This API is available only for Enterprise plans **upon request**. If you are interested, please [contact our support team](https://fingerprint.com/support/).  ### Rate limits and daily quota The rate limits and daily quota for this API **differ** from those for our other API.  The maximum number of DELETE requests that can be made in an hour cannot exceed 30 RPH, and the maximum number that can be made in a day cannot exceed 500 RPD.  You can request an increase to these limits by contacting [our support team](https://fingerprint.com/support/).
   * @param visitorId The [visitor ID](https://docs.fingerprint.com/reference/js-agent-v4-get-function#visitor_id) you want to delete. (required)
   * @return ApiResponse<Void>
   * @throws ApiException if fails to make API call
   * @http.response.details
   * <table summary="Response Details" border="1">
   * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
   * <tr><td> 200 </td><td> OK. The visitor ID is scheduled for deletion. </td><td>  -  </td></tr>
   * <tr><td> 400 </td><td> Bad request. The visitor ID parameter is missing or in the wrong format. </td><td>  -  </td></tr>
   * <tr><td> 403 </td><td> Forbidden. Access to this API is denied. </td><td>  -  </td></tr>
   * <tr><td> 404 </td><td> Not found. The visitor ID cannot be found in this workspace&#39;s data. </td><td>  -  </td></tr>
   * <tr><td> 429 </td><td> Too Many Requests. The request is throttled. </td><td>  -  </td></tr>
   * </table>
   */
  public ApiResponse<Void> deleteVisitorDataWithHttpInfo(String visitorId) throws ApiException {
    Object localVarPostBody = null;

    // verify the required parameter 'visitorId' is set
    if (visitorId == null) {
      throw new ApiException(
          400, "Missing the required parameter 'visitorId' when calling deleteVisitorData");
    }

    // create path and map variables
    String localVarPath =
        "/visitors/{visitor_id}"
            .replaceAll("\\{" + "visitor_id" + "\\}", apiClient.escapeString(visitorId.toString()));

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, String> localVarCookieParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.add(new Pair("ii", INTEGRATION_INFO));

    final String localVarAccept = apiClient.selectHeaderAccept("application/json");
    final String localVarContentType = apiClient.selectHeaderContentType();
    String[] localVarAuthNames = new String[] {"bearerAuth"};

    return apiClient.invokeAPI(
        "FingerprintApi.deleteVisitorData",
        localVarPath,
        "DELETE",
        localVarQueryParams,
        localVarPostBody,
        localVarHeaderParams,
        localVarCookieParams,
        localVarFormParams,
        localVarAccept,
        localVarContentType,
        localVarAuthNames,
        null,
        false);
  }

  public static class GetEventOptionalParams {

    private String rulesetId;

    /**
     * getter for rulesetId - The ID of the ruleset to evaluate against the event, producing the action to take for this event. The resulting action is returned in the `rule_action` attribute of the response.
     */
    public String getRulesetId() {
      return rulesetId;
    }

    /**
     * setter for rulesetId - The ID of the ruleset to evaluate against the event, producing the action to take for this event. The resulting action is returned in the `rule_action` attribute of the response.
     */
    public GetEventOptionalParams setRulesetId(String rulesetId) {
      this.rulesetId = rulesetId;
      return this;
    }
  }

  /**
   * Get an event by event ID
   * Get a detailed analysis of an individual identification event, including Smart Signals.  Use `event_id` as the URL path parameter. This API method is scoped to a request, i.e. all returned information is by `event_id`.
   * @param eventId The unique [identifier](https://docs.fingerprint.com/reference/js-agent-v4-get-function#event_id) of each identification request (`requestId` can be used in its place). (required)
   * @return Event
   * @throws ApiException if fails to make API call
   * @http.response.details
   * <table summary="Response Details" border="1">
   * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
   * <tr><td> 200 </td><td> OK. </td><td>  -  </td></tr>
   * <tr><td> 400 </td><td> Bad request. The event Id provided is not valid. </td><td>  -  </td></tr>
   * <tr><td> 403 </td><td> Forbidden. Access to this API is denied. </td><td>  -  </td></tr>
   * <tr><td> 404 </td><td> Not found. The event Id cannot be found in this workspace&#39;s data. </td><td>  -  </td></tr>
   * <tr><td> 429 </td><td> Too Many Requests. The request is throttled. </td><td>  -  </td></tr>
   * <tr><td> 500 </td><td> Workspace error. </td><td>  -  </td></tr>
   * </table>
   */
  public Event getEvent(String eventId) throws ApiException {
    return getEventWithHttpInfo(eventId, null).getData();
  }

  /**
   * Get an event by event ID
   * Get a detailed analysis of an individual identification event, including Smart Signals.  Use `event_id` as the URL path parameter. This API method is scoped to a request, i.e. all returned information is by `event_id`.
   * @param eventId The unique [identifier](https://docs.fingerprint.com/reference/js-agent-v4-get-function#event_id) of each identification request (`requestId` can be used in its place). (required)
   * @param getEventOptionalParams Object containing optional parameters for API method.  (optional)
   * @return Event
   * @throws ApiException if fails to make API call
   * @http.response.details
   * <table summary="Response Details" border="1">
   * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
   * <tr><td> 200 </td><td> OK. </td><td>  -  </td></tr>
   * <tr><td> 400 </td><td> Bad request. The event Id provided is not valid. </td><td>  -  </td></tr>
   * <tr><td> 403 </td><td> Forbidden. Access to this API is denied. </td><td>  -  </td></tr>
   * <tr><td> 404 </td><td> Not found. The event Id cannot be found in this workspace&#39;s data. </td><td>  -  </td></tr>
   * <tr><td> 429 </td><td> Too Many Requests. The request is throttled. </td><td>  -  </td></tr>
   * <tr><td> 500 </td><td> Workspace error. </td><td>  -  </td></tr>
   * </table>
   */
  public Event getEvent(String eventId, GetEventOptionalParams getEventOptionalParams)
      throws ApiException {
    return getEventWithHttpInfo(eventId, getEventOptionalParams).getData();
  }

  /**
   * Get an event by event ID
   * Get a detailed analysis of an individual identification event, including Smart Signals.  Use `event_id` as the URL path parameter. This API method is scoped to a request, i.e. all returned information is by `event_id`.
   * @param eventId The unique [identifier](https://docs.fingerprint.com/reference/js-agent-v4-get-function#event_id) of each identification request (`requestId` can be used in its place). (required)
   * @param getEventOptionalParams Object containing optional parameters for API method.  (optional)
   * @return ApiResponse<Event>
   * @throws ApiException if fails to make API call
   * @http.response.details
   * <table summary="Response Details" border="1">
   * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
   * <tr><td> 200 </td><td> OK. </td><td>  -  </td></tr>
   * <tr><td> 400 </td><td> Bad request. The event Id provided is not valid. </td><td>  -  </td></tr>
   * <tr><td> 403 </td><td> Forbidden. Access to this API is denied. </td><td>  -  </td></tr>
   * <tr><td> 404 </td><td> Not found. The event Id cannot be found in this workspace&#39;s data. </td><td>  -  </td></tr>
   * <tr><td> 429 </td><td> Too Many Requests. The request is throttled. </td><td>  -  </td></tr>
   * <tr><td> 500 </td><td> Workspace error. </td><td>  -  </td></tr>
   * </table>
   */
  public ApiResponse<Event> getEventWithHttpInfo(
      String eventId, GetEventOptionalParams getEventOptionalParams) throws ApiException {
    Object localVarPostBody = null;

    // verify the required parameter 'eventId' is set
    if (eventId == null) {
      throw new ApiException(400, "Missing the required parameter 'eventId' when calling getEvent");
    }

    // create path and map variables
    String localVarPath =
        "/events/{event_id}"
            .replaceAll("\\{" + "event_id" + "\\}", apiClient.escapeString(eventId.toString()));

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, String> localVarCookieParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.add(new Pair("ii", INTEGRATION_INFO));
    if (getEventOptionalParams != null) {
      localVarQueryParams.addAll(
          apiClient.parameterToPairs("", "ruleset_id", getEventOptionalParams.getRulesetId()));
    }

    final String localVarAccept = apiClient.selectHeaderAccept("application/json");
    final String localVarContentType = apiClient.selectHeaderContentType();
    String[] localVarAuthNames = new String[] {"bearerAuth"};

    GenericType<Event> localVarReturnType = new GenericType<Event>() {};

    return apiClient.invokeAPI(
        "FingerprintApi.getEvent",
        localVarPath,
        "GET",
        localVarQueryParams,
        localVarPostBody,
        localVarHeaderParams,
        localVarCookieParams,
        localVarFormParams,
        localVarAccept,
        localVarContentType,
        localVarAuthNames,
        localVarReturnType,
        false);
  }

  public static class SearchEventsOptionalParams {
    private Integer limit;
    private String paginationKey;
    private String visitorId;
    private String highRecallId;
    private SearchEventsBot bot;
    private SearchEventsBotInfo botInfo;
    private List<BotInfoCategory> botInfoCategory;
    private List<BotInfoIdentity> botInfoIdentity;
    private List<BotInfoConfidence> botInfoConfidence;
    private List<String> botInfoProvider;
    private List<String> botInfoName;
    private String ipAddress;
    private String asn;
    private String linkedId;
    private String url;
    private String bundleId;
    private String packageName;
    private String origin;
    private Long start;
    private OffsetDateTime startDateTime;
    private Long end;
    private OffsetDateTime endDateTime;
    private Boolean reverse;
    private Boolean suspect;
    private Boolean vpn;
    private Boolean virtualMachine;
    private Boolean tampering;
    private Boolean antiDetectBrowser;
    private Boolean incognito;
    private Boolean privacySettings;
    private Boolean jailbroken;
    private Boolean frida;
    private Boolean factoryReset;
    private Boolean clonedApp;
    private Boolean emulator;
    private Boolean rootApps;
    private SearchEventsVpnConfidence vpnConfidence;
    private Float minSuspectScore;
    private Boolean developerTools;
    private Boolean locationSpoofing;
    private Boolean mitmAttack;
    private Boolean rareDevice;
    private SearchEventsRareDevicePercentileBucket rareDevicePercentileBucket;
    private Boolean proxy;
    private String sdkVersion;
    private SearchEventsSdkPlatform sdkPlatform;
    private List<String> environment;
    private String proximityId;
    private Long totalHits;
    private Boolean torNode;
    private SearchEventsIncrementalIdentificationStatus incrementalIdentificationStatus;
    private Boolean simulator;
    private List<SearchEventsSource> source;

    /**
     * getter for limit - Maximum number of events to return. Defaults to 10 when omitted. Results are selected from the time range (`start`, `end`), ordered by `reverse`, then truncated to provided `limit` size. So `reverse=true` returns the oldest N=`limit` events, otherwise the newest N=`limit` events.
     */
    public Integer getLimit() {
      return limit;
    }

    /**
     * setter for limit - Maximum number of events to return. Defaults to 10 when omitted. Results are selected from the time range (`start`, `end`), ordered by `reverse`, then truncated to provided `limit` size. So `reverse=true` returns the oldest N=`limit` events, otherwise the newest N=`limit` events.
     */
    public SearchEventsOptionalParams setLimit(Integer limit) {
      this.limit = limit;
      return this;
    }

    /**
     * getter for paginationKey - Use `pagination_key` to get the next page of results.  When more results are available (e.g., you requested up to 100 results for your query using `limit`, but there are more than 100 events total matching your request), the `pagination_key` field is added to the response. The pagination key is an arbitrary string that should not be interpreted in any way and should be passed as-is. In the following request, use that value in the `pagination_key` parameter to get the next page of results:  1. First request, returning most recent 100 events: `GET api-base-url/events?limit=100` 2. Use `response.pagination_key` to get the next page of results: `GET api-base-url/events?limit=100&pagination_key=S9rgMMUb4z3X5t5pr_tSgoSZlmyF0O8X7kCV2m981-iY1LmRTjraa1rTk3L-hQExnDWCi0RA-zAIjaVSTNO2AN2eqQWgzT0RjbieMxRfSdkM-HmOhdOgdQvYfPG3vqU1DJKh4Q`
     */
    public String getPaginationKey() {
      return paginationKey;
    }

    /**
     * setter for paginationKey - Use `pagination_key` to get the next page of results.  When more results are available (e.g., you requested up to 100 results for your query using `limit`, but there are more than 100 events total matching your request), the `pagination_key` field is added to the response. The pagination key is an arbitrary string that should not be interpreted in any way and should be passed as-is. In the following request, use that value in the `pagination_key` parameter to get the next page of results:  1. First request, returning most recent 100 events: `GET api-base-url/events?limit=100` 2. Use `response.pagination_key` to get the next page of results: `GET api-base-url/events?limit=100&pagination_key=S9rgMMUb4z3X5t5pr_tSgoSZlmyF0O8X7kCV2m981-iY1LmRTjraa1rTk3L-hQExnDWCi0RA-zAIjaVSTNO2AN2eqQWgzT0RjbieMxRfSdkM-HmOhdOgdQvYfPG3vqU1DJKh4Q`
     */
    public SearchEventsOptionalParams setPaginationKey(String paginationKey) {
      this.paginationKey = paginationKey;
      return this;
    }

    /**
     * getter for visitorId - Unique [visitor identifier](https://docs.fingerprint.com/reference/js-agent-v4-get-function#visitor_id) issued by Fingerprint Identification and all active Smart Signals.  Filter events by matching Visitor ID (`identification.visitor_id` property).
     */
    public String getVisitorId() {
      return visitorId;
    }

    /**
     * setter for visitorId - Unique [visitor identifier](https://docs.fingerprint.com/reference/js-agent-v4-get-function#visitor_id) issued by Fingerprint Identification and all active Smart Signals.  Filter events by matching Visitor ID (`identification.visitor_id` property).
     */
    public SearchEventsOptionalParams setVisitorId(String visitorId) {
      this.visitorId = visitorId;
      return this;
    }

    /**
     * getter for highRecallId - The High Recall ID is a supplementary browser identifier designed for use cases that require wider coverage over precision. Compared to the standard visitor ID, the High Recall ID strives to match incoming browsers more generously (rather than precisely) with existing browsers and thus identifies fewer browsers as new. The High Recall ID is best suited for use cases that are sensitive to browsers being identified as new and where mismatched browsers are not detrimental.  Filter events by matching High Recall ID (`supplementary_id_high_recall.visitor_id` property).
     */
    public String getHighRecallId() {
      return highRecallId;
    }

    /**
     * setter for highRecallId - The High Recall ID is a supplementary browser identifier designed for use cases that require wider coverage over precision. Compared to the standard visitor ID, the High Recall ID strives to match incoming browsers more generously (rather than precisely) with existing browsers and thus identifies fewer browsers as new. The High Recall ID is best suited for use cases that are sensitive to browsers being identified as new and where mismatched browsers are not detrimental.  Filter events by matching High Recall ID (`supplementary_id_high_recall.visitor_id` property).
     */
    public SearchEventsOptionalParams setHighRecallId(String highRecallId) {
      this.highRecallId = highRecallId;
      return this;
    }

    /**
     * getter for bot - Filter events by the Bot Detection result, specifically:   `all` - events where any kind of bot was detected.   `good` - events where a good bot was detected.   `bad` - events where a bad bot was detected.   `none` - events where no bot was detected. > Note: When using this parameter, only events with the `bot` property set to a valid value are returned. Events without a `bot` Smart Signal result are left out of the response.
     */
    public SearchEventsBot getBot() {
      return bot;
    }

    /**
     * setter for bot - Filter events by the Bot Detection result, specifically:   `all` - events where any kind of bot was detected.   `good` - events where a good bot was detected.   `bad` - events where a bad bot was detected.   `none` - events where no bot was detected. > Note: When using this parameter, only events with the `bot` property set to a valid value are returned. Events without a `bot` Smart Signal result are left out of the response.
     */
    public SearchEventsOptionalParams setBot(SearchEventsBot bot) {
      this.bot = bot;
      return this;
    }

    /**
     * getter for botInfo - Filter events by their Bot Info result, specifically:   - `all` - events where any kind of bot was detected.   - `none` - events where no bot was detected.
     */
    public SearchEventsBotInfo getBotInfo() {
      return botInfo;
    }

    /**
     * setter for botInfo - Filter events by their Bot Info result, specifically:   - `all` - events where any kind of bot was detected.   - `none` - events where no bot was detected.
     */
    public SearchEventsOptionalParams setBotInfo(SearchEventsBotInfo botInfo) {
      this.botInfo = botInfo;
      return this;
    }

    /**
     * getter for botInfoCategory - Filter events by their Bot Info Category.  Multiple categories can be provided using the repeated keys syntax. For example, `bot_info_category=ai_agent&bot_info_category=ai_assistant`, will match events with a Bot Info Category of `ai_agent` or `ai_assistant`. Other notations like comma-separated or bracket notation are not supported.
     */
    public List<BotInfoCategory> getBotInfoCategory() {
      return botInfoCategory;
    }

    /**
     * setter for botInfoCategory - Filter events by their Bot Info Category.  Multiple categories can be provided using the repeated keys syntax. For example, `bot_info_category=ai_agent&bot_info_category=ai_assistant`, will match events with a Bot Info Category of `ai_agent` or `ai_assistant`. Other notations like comma-separated or bracket notation are not supported.
     */
    public SearchEventsOptionalParams setBotInfoCategory(List<BotInfoCategory> botInfoCategory) {
      this.botInfoCategory = botInfoCategory;
      return this;
    }

    /**
     * getter for botInfoIdentity - Filter events by their Bot Info Identity type.  Multiple identity types can be provided using the repeated keys syntax. For example, `bot_info_identity=verified&bot_info_identity=signed`, will match events with a Bot Info Identity of `verified` or `signed`. Other notations like comma-separated or bracket notation are not supported.
     */
    public List<BotInfoIdentity> getBotInfoIdentity() {
      return botInfoIdentity;
    }

    /**
     * setter for botInfoIdentity - Filter events by their Bot Info Identity type.  Multiple identity types can be provided using the repeated keys syntax. For example, `bot_info_identity=verified&bot_info_identity=signed`, will match events with a Bot Info Identity of `verified` or `signed`. Other notations like comma-separated or bracket notation are not supported.
     */
    public SearchEventsOptionalParams setBotInfoIdentity(List<BotInfoIdentity> botInfoIdentity) {
      this.botInfoIdentity = botInfoIdentity;
      return this;
    }

    /**
     * getter for botInfoConfidence - Filter events by their Bot Info Confidence.  Multiple confidences can be provided using the repeated keys syntax. For example, `bot_info_confidence=high&bot_info_confidence=medium`, will match events with a Bot Info Confidence of `high` or `medium`. Other notations like comma-separated or bracket notation are not supported.
     */
    public List<BotInfoConfidence> getBotInfoConfidence() {
      return botInfoConfidence;
    }

    /**
     * setter for botInfoConfidence - Filter events by their Bot Info Confidence.  Multiple confidences can be provided using the repeated keys syntax. For example, `bot_info_confidence=high&bot_info_confidence=medium`, will match events with a Bot Info Confidence of `high` or `medium`. Other notations like comma-separated or bracket notation are not supported.
     */
    public SearchEventsOptionalParams setBotInfoConfidence(
        List<BotInfoConfidence> botInfoConfidence) {
      this.botInfoConfidence = botInfoConfidence;
      return this;
    }

    /**
     * getter for botInfoProvider - Filter events by their Bot Info Provider. The provider must match exactly, partial or wildcard matching is not supported.  Multiple Providers can be provided using the repeated keys syntax. For example, `bot_info_provider=OpenAI&bot_info_provider=AWS`, will match events with a Bot Info Provider of `OpenAI` or `AWS`. Other notations like comma-separated or bracket notation are not supported.
     */
    public List<String> getBotInfoProvider() {
      return botInfoProvider;
    }

    /**
     * setter for botInfoProvider - Filter events by their Bot Info Provider. The provider must match exactly, partial or wildcard matching is not supported.  Multiple Providers can be provided using the repeated keys syntax. For example, `bot_info_provider=OpenAI&bot_info_provider=AWS`, will match events with a Bot Info Provider of `OpenAI` or `AWS`. Other notations like comma-separated or bracket notation are not supported.
     */
    public SearchEventsOptionalParams setBotInfoProvider(List<String> botInfoProvider) {
      this.botInfoProvider = botInfoProvider;
      return this;
    }

    /**
     * getter for botInfoName - Filter events by their Bot Info Name. The name must match exactly, partial or wildcard matching is not supported.  Multiple Names can be provided using the repeated keys syntax. For example, `bot_info_name=ChatGPT%20Agent&bot_info_name=Bedrock%20AgentCore`, will match events with a Bot Info Name of `ChatGPT Agent` or `Bedrock AgentCore`. Other notations like comma-separated or bracket notation are not supported.
     */
    public List<String> getBotInfoName() {
      return botInfoName;
    }

    /**
     * setter for botInfoName - Filter events by their Bot Info Name. The name must match exactly, partial or wildcard matching is not supported.  Multiple Names can be provided using the repeated keys syntax. For example, `bot_info_name=ChatGPT%20Agent&bot_info_name=Bedrock%20AgentCore`, will match events with a Bot Info Name of `ChatGPT Agent` or `Bedrock AgentCore`. Other notations like comma-separated or bracket notation are not supported.
     */
    public SearchEventsOptionalParams setBotInfoName(List<String> botInfoName) {
      this.botInfoName = botInfoName;
      return this;
    }

    /**
     * getter for ipAddress - Filter events by IP address or IP range (if CIDR notation is used). If CIDR notation is not used, a /32 for IPv4 or /128 for IPv6 is assumed. Examples of range based queries: 10.0.0.0/24, 192.168.0.1/32
     */
    public String getIpAddress() {
      return ipAddress;
    }

    /**
     * setter for ipAddress - Filter events by IP address or IP range (if CIDR notation is used). If CIDR notation is not used, a /32 for IPv4 or /128 for IPv6 is assumed. Examples of range based queries: 10.0.0.0/24, 192.168.0.1/32
     */
    public SearchEventsOptionalParams setIpAddress(String ipAddress) {
      this.ipAddress = ipAddress;
      return this;
    }

    /**
     * getter for asn - Filter events by the ASN associated with the event's IP address. This corresponds to the `ip_info.(v4|v6).asn` property in the response.
     */
    public String getAsn() {
      return asn;
    }

    /**
     * setter for asn - Filter events by the ASN associated with the event's IP address. This corresponds to the `ip_info.(v4|v6).asn` property in the response.
     */
    public SearchEventsOptionalParams setAsn(String asn) {
      this.asn = asn;
      return this;
    }

    /**
     * getter for linkedId - Filter events by your custom identifier.  You can use [linked Ids](https://docs.fingerprint.com/reference/js-agent-v4-get-function#linkedid) to associate identification requests with your own identifier, for example, session Id, purchase Id, or transaction Id. You can then use this `linked_id` parameter to retrieve all events associated with your custom identifier.
     */
    public String getLinkedId() {
      return linkedId;
    }

    /**
     * setter for linkedId - Filter events by your custom identifier.  You can use [linked Ids](https://docs.fingerprint.com/reference/js-agent-v4-get-function#linkedid) to associate identification requests with your own identifier, for example, session Id, purchase Id, or transaction Id. You can then use this `linked_id` parameter to retrieve all events associated with your custom identifier.
     */
    public SearchEventsOptionalParams setLinkedId(String linkedId) {
      this.linkedId = linkedId;
      return this;
    }

    /**
     * getter for url - Filter events by the URL (`url` property) associated with the event.
     */
    public String getUrl() {
      return url;
    }

    /**
     * setter for url - Filter events by the URL (`url` property) associated with the event.
     */
    public SearchEventsOptionalParams setUrl(String url) {
      this.url = url;
      return this;
    }

    /**
     * getter for bundleId - Filter events by the Bundle ID (iOS) associated with the event.
     */
    public String getBundleId() {
      return bundleId;
    }

    /**
     * setter for bundleId - Filter events by the Bundle ID (iOS) associated with the event.
     */
    public SearchEventsOptionalParams setBundleId(String bundleId) {
      this.bundleId = bundleId;
      return this;
    }

    /**
     * getter for packageName - Filter events by the Package Name (Android) associated with the event.
     */
    public String getPackageName() {
      return packageName;
    }

    /**
     * setter for packageName - Filter events by the Package Name (Android) associated with the event.
     */
    public SearchEventsOptionalParams setPackageName(String packageName) {
      this.packageName = packageName;
      return this;
    }

    /**
     * getter for origin - Filter events by the origin field of the event. This is applicable to web events only (e.g., https://example.com)
     */
    public String getOrigin() {
      return origin;
    }

    /**
     * setter for origin - Filter events by the origin field of the event. This is applicable to web events only (e.g., https://example.com)
     */
    public SearchEventsOptionalParams setOrigin(String origin) {
      this.origin = origin;
      return this;
    }

    /**
     * getter for start - Include events that happened after this point (with timestamp greater than or equal to the provided `start` Unix milliseconds value). Defaults to 7 days ago. Setting `start` does not change the default of `now` for `end`/`end_date_time` — adjust it separately if needed.
     *
     * @see {@link #getStartDateTime}
     */
    public Long getStart() {
      return start;
    }

    /**
     * setter for start - Include events that happened after this point (with timestamp greater than or equal to the provided `start` Unix milliseconds value). Defaults to 7 days ago. Setting `start` does not change the default of `now` for `end`/`end_date_time` — adjust it separately if needed.
     *
     * start is an alias for startDateTime. Invoking {@link #setStart} will also set `startDateTime` to `null` to clear an existing `startDateTime` parameter value.
     *
     * @see {@link #setStartDateTime}
     */
    public SearchEventsOptionalParams setStart(Long start) {
      this.start = start;
      this.startDateTime = null;
      return this;
    }

    /**
     * getter for startDateTime - Include events that happened after this point (with timestamp greater than or equal to the provided `start_date_time` RFC3339 timestamp). Defaults to 7 days ago. Setting `start_date_time` does not the default of `now` for `end`/`end_date_time` — adjust it separately if needed. This parameter is an alias for `start`.
     *
     * @see {@link #getStart}
     */
    public OffsetDateTime getStartDateTime() {
      return startDateTime;
    }

    /**
     * setter for startDateTime - Include events that happened after this point (with timestamp greater than or equal to the provided `start_date_time` RFC3339 timestamp). Defaults to 7 days ago. Setting `start_date_time` does not the default of `now` for `end`/`end_date_time` — adjust it separately if needed. This parameter is an alias for `start`.
     *
     * startDateTime is an alias for start. Invoking {@link #setStartDateTime} will also set `start` to `null` to clear an existing `start` parameter value.
     *
     * @see {@link #setStart}
     */
    public SearchEventsOptionalParams setStartDateTime(OffsetDateTime startDateTime) {
      this.startDateTime = startDateTime;
      this.start = null;
      return this;
    }

    /**
     * getter for end - Include events that happened before this point (with timestamp less than or equal the provided `end` Unix milliseconds value). Defaults to now. Setting `end` does not change the default of `7 days ago` for `start`/`start_date_time` — adjust it separately if needed.
     *
     * @see {@link #getEndDateTime}
     */
    public Long getEnd() {
      return end;
    }

    /**
     * setter for end - Include events that happened before this point (with timestamp less than or equal the provided `end` Unix milliseconds value). Defaults to now. Setting `end` does not change the default of `7 days ago` for `start`/`start_date_time` — adjust it separately if needed.
     *
     * end is an alias for endDateTime. Invoking {@link #setEnd} will also set `endDateTime` to `null` to clear an existing `endDateTime` parameter value.
     *
     * @see {@link #setEndDateTime}
     */
    public SearchEventsOptionalParams setEnd(Long end) {
      this.end = end;
      this.endDateTime = null;
      return this;
    }

    /**
     * getter for endDateTime - Include events that happened before this point (with timestamp less than or equal the provided `end_date_time` RFC3339 timestamp). Defaults to now. Setting `end_date_time` does not change the default of `7 days ago` for `start`/`start_date_time` — adjust it separately if needed. This parameter is an alias for `end`.
     *
     * @see {@link #getEnd}
     */
    public OffsetDateTime getEndDateTime() {
      return endDateTime;
    }

    /**
     * setter for endDateTime - Include events that happened before this point (with timestamp less than or equal the provided `end_date_time` RFC3339 timestamp). Defaults to now. Setting `end_date_time` does not change the default of `7 days ago` for `start`/`start_date_time` — adjust it separately if needed. This parameter is an alias for `end`.
     *
     * endDateTime is an alias for end. Invoking {@link #setEndDateTime} will also set `end` to `null` to clear an existing `end` parameter value.
     *
     * @see {@link #setEnd}
     */
    public SearchEventsOptionalParams setEndDateTime(OffsetDateTime endDateTime) {
      this.endDateTime = endDateTime;
      this.end = null;
      return this;
    }

    /**
     * getter for reverse - When `true`, sort events oldest first (ascending timestamp order). Defaults to `false` (newest first, descending timestamp order).
     */
    public Boolean getReverse() {
      return reverse;
    }

    /**
     * setter for reverse - When `true`, sort events oldest first (ascending timestamp order). Defaults to `false` (newest first, descending timestamp order).
     */
    public SearchEventsOptionalParams setReverse(Boolean reverse) {
      this.reverse = reverse;
      return this;
    }

    /**
     * getter for suspect - Filter events previously tagged as suspicious via the [Update API](https://docs.fingerprint.com/reference/server-api-v4-update-event). > Note: When using this parameter, only events with the `suspect` property explicitly set to `true` or `false` are returned. Events with undefined `suspect` property are left out of the response.
     */
    public Boolean getSuspect() {
      return suspect;
    }

    /**
     * setter for suspect - Filter events previously tagged as suspicious via the [Update API](https://docs.fingerprint.com/reference/server-api-v4-update-event). > Note: When using this parameter, only events with the `suspect` property explicitly set to `true` or `false` are returned. Events with undefined `suspect` property are left out of the response.
     */
    public SearchEventsOptionalParams setSuspect(Boolean suspect) {
      this.suspect = suspect;
      return this;
    }

    /**
     * getter for vpn - Filter events by VPN Detection result. > Note: When using this parameter, only events with the `vpn` property set to `true` or `false` are returned. Events without a `vpn` Smart Signal result are left out of the response.
     */
    public Boolean getVpn() {
      return vpn;
    }

    /**
     * setter for vpn - Filter events by VPN Detection result. > Note: When using this parameter, only events with the `vpn` property set to `true` or `false` are returned. Events without a `vpn` Smart Signal result are left out of the response.
     */
    public SearchEventsOptionalParams setVpn(Boolean vpn) {
      this.vpn = vpn;
      return this;
    }

    /**
     * getter for virtualMachine - Filter events by Virtual Machine Detection result. > Note: When using this parameter, only events with the `virtual_machine` property set to `true` or `false` are returned. Events without a `virtual_machine` Smart Signal result are left out of the response.
     */
    public Boolean getVirtualMachine() {
      return virtualMachine;
    }

    /**
     * setter for virtualMachine - Filter events by Virtual Machine Detection result. > Note: When using this parameter, only events with the `virtual_machine` property set to `true` or `false` are returned. Events without a `virtual_machine` Smart Signal result are left out of the response.
     */
    public SearchEventsOptionalParams setVirtualMachine(Boolean virtualMachine) {
      this.virtualMachine = virtualMachine;
      return this;
    }

    /**
     * getter for tampering - Filter events by Browser Tampering Detection result. > Note: When using this parameter, only events with the `tampering.result` property set to `true` or `false` are returned. Events without a `tampering` Smart Signal result are left out of the response.
     */
    public Boolean getTampering() {
      return tampering;
    }

    /**
     * setter for tampering - Filter events by Browser Tampering Detection result. > Note: When using this parameter, only events with the `tampering.result` property set to `true` or `false` are returned. Events without a `tampering` Smart Signal result are left out of the response.
     */
    public SearchEventsOptionalParams setTampering(Boolean tampering) {
      this.tampering = tampering;
      return this;
    }

    /**
     * getter for antiDetectBrowser - Filter events by Anti-detect Browser Detection result. > Note: When using this parameter, only events with the `tampering.anti_detect_browser` property set to `true` or `false` are returned. Events without a `tampering` Smart Signal result are left out of the response.
     */
    public Boolean getAntiDetectBrowser() {
      return antiDetectBrowser;
    }

    /**
     * setter for antiDetectBrowser - Filter events by Anti-detect Browser Detection result. > Note: When using this parameter, only events with the `tampering.anti_detect_browser` property set to `true` or `false` are returned. Events without a `tampering` Smart Signal result are left out of the response.
     */
    public SearchEventsOptionalParams setAntiDetectBrowser(Boolean antiDetectBrowser) {
      this.antiDetectBrowser = antiDetectBrowser;
      return this;
    }

    /**
     * getter for incognito - Filter events by Browser Incognito Detection result. > Note: When using this parameter, only events with the `incognito` property set to `true` or `false` are returned. Events without an `incognito` Smart Signal result are left out of the response.
     */
    public Boolean getIncognito() {
      return incognito;
    }

    /**
     * setter for incognito - Filter events by Browser Incognito Detection result. > Note: When using this parameter, only events with the `incognito` property set to `true` or `false` are returned. Events without an `incognito` Smart Signal result are left out of the response.
     */
    public SearchEventsOptionalParams setIncognito(Boolean incognito) {
      this.incognito = incognito;
      return this;
    }

    /**
     * getter for privacySettings - Filter events by Privacy Settings Detection result. > Note: When using this parameter, only events with the `privacy_settings` property set to `true` or `false` are returned. Events without a `privacy_settings` Smart Signal result are left out of the response.
     */
    public Boolean getPrivacySettings() {
      return privacySettings;
    }

    /**
     * setter for privacySettings - Filter events by Privacy Settings Detection result. > Note: When using this parameter, only events with the `privacy_settings` property set to `true` or `false` are returned. Events without a `privacy_settings` Smart Signal result are left out of the response.
     */
    public SearchEventsOptionalParams setPrivacySettings(Boolean privacySettings) {
      this.privacySettings = privacySettings;
      return this;
    }

    /**
     * getter for jailbroken - Filter events by Jailbroken Device Detection result. > Note: When using this parameter, only events with the `jailbroken` property set to `true` or `false` are returned. Events without a `jailbroken` Smart Signal result are left out of the response.
     */
    public Boolean getJailbroken() {
      return jailbroken;
    }

    /**
     * setter for jailbroken - Filter events by Jailbroken Device Detection result. > Note: When using this parameter, only events with the `jailbroken` property set to `true` or `false` are returned. Events without a `jailbroken` Smart Signal result are left out of the response.
     */
    public SearchEventsOptionalParams setJailbroken(Boolean jailbroken) {
      this.jailbroken = jailbroken;
      return this;
    }

    /**
     * getter for frida - Filter events by Frida Detection result. > Note: When using this parameter, only events with the `frida` property set to `true` or `false` are returned. Events without a `frida` Smart Signal result are left out of the response.
     */
    public Boolean getFrida() {
      return frida;
    }

    /**
     * setter for frida - Filter events by Frida Detection result. > Note: When using this parameter, only events with the `frida` property set to `true` or `false` are returned. Events without a `frida` Smart Signal result are left out of the response.
     */
    public SearchEventsOptionalParams setFrida(Boolean frida) {
      this.frida = frida;
      return this;
    }

    /**
     * getter for factoryReset - Filter events by Factory Reset Detection result. > Note: When using this parameter, only events with a `factory_reset` time. Events without a `factory_reset` Smart Signal result are left out of the response.
     */
    public Boolean getFactoryReset() {
      return factoryReset;
    }

    /**
     * setter for factoryReset - Filter events by Factory Reset Detection result. > Note: When using this parameter, only events with a `factory_reset` time. Events without a `factory_reset` Smart Signal result are left out of the response.
     */
    public SearchEventsOptionalParams setFactoryReset(Boolean factoryReset) {
      this.factoryReset = factoryReset;
      return this;
    }

    /**
     * getter for clonedApp - Filter events by Cloned App Detection result. > Note: When using this parameter, only events with the `cloned_app` property set to `true` or `false` are returned. Events without a `cloned_app` Smart Signal result are left out of the response.
     */
    public Boolean getClonedApp() {
      return clonedApp;
    }

    /**
     * setter for clonedApp - Filter events by Cloned App Detection result. > Note: When using this parameter, only events with the `cloned_app` property set to `true` or `false` are returned. Events without a `cloned_app` Smart Signal result are left out of the response.
     */
    public SearchEventsOptionalParams setClonedApp(Boolean clonedApp) {
      this.clonedApp = clonedApp;
      return this;
    }

    /**
     * getter for emulator - Filter events by Android Emulator Detection result. > Note: When using this parameter, only events with the `emulator` property set to `true` or `false` are returned. Events without an `emulator` Smart Signal result are left out of the response.
     */
    public Boolean getEmulator() {
      return emulator;
    }

    /**
     * setter for emulator - Filter events by Android Emulator Detection result. > Note: When using this parameter, only events with the `emulator` property set to `true` or `false` are returned. Events without an `emulator` Smart Signal result are left out of the response.
     */
    public SearchEventsOptionalParams setEmulator(Boolean emulator) {
      this.emulator = emulator;
      return this;
    }

    /**
     * getter for rootApps - Filter events by Rooted Device Detection result. > Note: When using this parameter, only events with the `root_apps` property set to `true` or `false` are returned. Events without a `root_apps` Smart Signal result are left out of the response.
     */
    public Boolean getRootApps() {
      return rootApps;
    }

    /**
     * setter for rootApps - Filter events by Rooted Device Detection result. > Note: When using this parameter, only events with the `root_apps` property set to `true` or `false` are returned. Events without a `root_apps` Smart Signal result are left out of the response.
     */
    public SearchEventsOptionalParams setRootApps(Boolean rootApps) {
      this.rootApps = rootApps;
      return this;
    }

    /**
     * getter for vpnConfidence - Filter events by VPN Detection result confidence level. `high` - events with high VPN Detection confidence. `medium` - events with medium VPN Detection confidence. `low` - events with low VPN Detection confidence. > Note: When using this parameter, only events with the `vpn.confidence` property set to a valid value are returned. Events without a `vpn` Smart Signal result are left out of the response.
     */
    public SearchEventsVpnConfidence getVpnConfidence() {
      return vpnConfidence;
    }

    /**
     * setter for vpnConfidence - Filter events by VPN Detection result confidence level. `high` - events with high VPN Detection confidence. `medium` - events with medium VPN Detection confidence. `low` - events with low VPN Detection confidence. > Note: When using this parameter, only events with the `vpn.confidence` property set to a valid value are returned. Events without a `vpn` Smart Signal result are left out of the response.
     */
    public SearchEventsOptionalParams setVpnConfidence(SearchEventsVpnConfidence vpnConfidence) {
      this.vpnConfidence = vpnConfidence;
      return this;
    }

    /**
     * getter for minSuspectScore - Filter events with Suspect Score result above a provided minimum threshold. > Note: When using this parameter, only events where the `suspect_score` property set to a value exceeding your threshold are returned. Events without a `suspect_score` Smart Signal result are left out of the response.
     */
    public Float getMinSuspectScore() {
      return minSuspectScore;
    }

    /**
     * setter for minSuspectScore - Filter events with Suspect Score result above a provided minimum threshold. > Note: When using this parameter, only events where the `suspect_score` property set to a value exceeding your threshold are returned. Events without a `suspect_score` Smart Signal result are left out of the response.
     */
    public SearchEventsOptionalParams setMinSuspectScore(Float minSuspectScore) {
      this.minSuspectScore = minSuspectScore;
      return this;
    }

    /**
     * getter for developerTools - Filter events by Developer Tools detection result. > Note: When using this parameter, only events with the `developer_tools` property set to `true` or `false` are returned. Events without a `developer_tools` Smart Signal result are left out of the response.
     */
    public Boolean getDeveloperTools() {
      return developerTools;
    }

    /**
     * setter for developerTools - Filter events by Developer Tools detection result. > Note: When using this parameter, only events with the `developer_tools` property set to `true` or `false` are returned. Events without a `developer_tools` Smart Signal result are left out of the response.
     */
    public SearchEventsOptionalParams setDeveloperTools(Boolean developerTools) {
      this.developerTools = developerTools;
      return this;
    }

    /**
     * getter for locationSpoofing - Filter events by Location Spoofing detection result. > Note: When using this parameter, only events with the `location_spoofing` property set to `true` or `false` are returned. Events without a `location_spoofing` Smart Signal result are left out of the response.
     */
    public Boolean getLocationSpoofing() {
      return locationSpoofing;
    }

    /**
     * setter for locationSpoofing - Filter events by Location Spoofing detection result. > Note: When using this parameter, only events with the `location_spoofing` property set to `true` or `false` are returned. Events without a `location_spoofing` Smart Signal result are left out of the response.
     */
    public SearchEventsOptionalParams setLocationSpoofing(Boolean locationSpoofing) {
      this.locationSpoofing = locationSpoofing;
      return this;
    }

    /**
     * getter for mitmAttack - Filter events by MITM (Man-in-the-Middle) Attack detection result. > Note: When using this parameter, only events with the `mitm_attack` property set to `true` or `false` are returned. Events without a `mitm_attack` Smart Signal result are left out of the response.
     */
    public Boolean getMitmAttack() {
      return mitmAttack;
    }

    /**
     * setter for mitmAttack - Filter events by MITM (Man-in-the-Middle) Attack detection result. > Note: When using this parameter, only events with the `mitm_attack` property set to `true` or `false` are returned. Events without a `mitm_attack` Smart Signal result are left out of the response.
     */
    public SearchEventsOptionalParams setMitmAttack(Boolean mitmAttack) {
      this.mitmAttack = mitmAttack;
      return this;
    }

    /**
     * getter for rareDevice - Filter events by Device Rarity detection result. > Note: When using this parameter, only events with the `rare_device` property set to `true` or `false` are returned. Events without a Device Rarity Smart Signal result are left out of the response.  > This Smart Signal is currently in beta and only available to select customers. If you are interested, please [contact our support team](https://fingerprint.com/support/).
     */
    public Boolean getRareDevice() {
      return rareDevice;
    }

    /**
     * setter for rareDevice - Filter events by Device Rarity detection result. > Note: When using this parameter, only events with the `rare_device` property set to `true` or `false` are returned. Events without a Device Rarity Smart Signal result are left out of the response.  > This Smart Signal is currently in beta and only available to select customers. If you are interested, please [contact our support team](https://fingerprint.com/support/).
     */
    public SearchEventsOptionalParams setRareDevice(Boolean rareDevice) {
      this.rareDevice = rareDevice;
      return this;
    }

    /**
     * getter for rareDevicePercentileBucket - Filter events by Device Rarity percentile bucket. `<p95` - device configuration is in the bottom 95% (most common). `p95-p99` - device is in the 95th to 99th percentile. `p99-p99.5` - device is in the 99th to 99.5th percentile. `p99.5-p99.9` - device is in the 99.5th to 99.9th percentile. `p99.9+` - device is in the top 0.1% (rarest). `not_seen` - device configuration has never been observed before.  > This Smart Signal is currently in beta and only available to select customers. If you are interested, please [contact our support team](https://fingerprint.com/support/).
     */
    public SearchEventsRareDevicePercentileBucket getRareDevicePercentileBucket() {
      return rareDevicePercentileBucket;
    }

    /**
     * setter for rareDevicePercentileBucket - Filter events by Device Rarity percentile bucket. `<p95` - device configuration is in the bottom 95% (most common). `p95-p99` - device is in the 95th to 99th percentile. `p99-p99.5` - device is in the 99th to 99.5th percentile. `p99.5-p99.9` - device is in the 99.5th to 99.9th percentile. `p99.9+` - device is in the top 0.1% (rarest). `not_seen` - device configuration has never been observed before.  > This Smart Signal is currently in beta and only available to select customers. If you are interested, please [contact our support team](https://fingerprint.com/support/).
     */
    public SearchEventsOptionalParams setRareDevicePercentileBucket(
        SearchEventsRareDevicePercentileBucket rareDevicePercentileBucket) {
      this.rareDevicePercentileBucket = rareDevicePercentileBucket;
      return this;
    }

    /**
     * getter for proxy - Filter events by Proxy detection result. > Note: When using this parameter, only events with the `proxy` property set to `true` or `false` are returned. Events without a `proxy` Smart Signal result are left out of the response.
     */
    public Boolean getProxy() {
      return proxy;
    }

    /**
     * setter for proxy - Filter events by Proxy detection result. > Note: When using this parameter, only events with the `proxy` property set to `true` or `false` are returned. Events without a `proxy` Smart Signal result are left out of the response.
     */
    public SearchEventsOptionalParams setProxy(Boolean proxy) {
      this.proxy = proxy;
      return this;
    }

    /**
     * getter for sdkVersion - Filter events by a specific SDK version associated with the identification event (`sdk.version` property). Example: `3.11.14`
     */
    public String getSdkVersion() {
      return sdkVersion;
    }

    /**
     * setter for sdkVersion - Filter events by a specific SDK version associated with the identification event (`sdk.version` property). Example: `3.11.14`
     */
    public SearchEventsOptionalParams setSdkVersion(String sdkVersion) {
      this.sdkVersion = sdkVersion;
      return this;
    }

    /**
     * getter for sdkPlatform - Filter events by the SDK Platform associated with the identification event (`sdk.platform` property) . `js` - Javascript agent (Web). `ios` - Apple iOS based devices. `android` - Android based devices.
     */
    public SearchEventsSdkPlatform getSdkPlatform() {
      return sdkPlatform;
    }

    /**
     * setter for sdkPlatform - Filter events by the SDK Platform associated with the identification event (`sdk.platform` property) . `js` - Javascript agent (Web). `ios` - Apple iOS based devices. `android` - Android based devices.
     */
    public SearchEventsOptionalParams setSdkPlatform(SearchEventsSdkPlatform sdkPlatform) {
      this.sdkPlatform = sdkPlatform;
      return this;
    }

    /**
     * getter for environment - Filter for events by providing one or more environment IDs (`environment_id` property).  ### Array syntax To provide multiple environment IDs, use the repeated keys syntax (`environment=env1&environment=env2`). Other notations like comma-separated (`environment=env1,env2`) or bracket notation (`environment[]=env1&environment[]=env2`) are not supported.
     */
    public List<String> getEnvironment() {
      return environment;
    }

    /**
     * setter for environment - Filter for events by providing one or more environment IDs (`environment_id` property).  ### Array syntax To provide multiple environment IDs, use the repeated keys syntax (`environment=env1&environment=env2`). Other notations like comma-separated (`environment=env1,env2`) or bracket notation (`environment[]=env1&environment[]=env2`) are not supported.
     */
    public SearchEventsOptionalParams setEnvironment(List<String> environment) {
      this.environment = environment;
      return this;
    }

    /**
     * getter for proximityId - Filter events by the most precise Proximity ID provided by default. > Note: When using this parameter, only events with the `proximity.id` property matching the provided ID are returned. Events without a `proximity` result are left out of the response.
     */
    public String getProximityId() {
      return proximityId;
    }

    /**
     * setter for proximityId - Filter events by the most precise Proximity ID provided by default. > Note: When using this parameter, only events with the `proximity.id` property matching the provided ID are returned. Events without a `proximity` result are left out of the response.
     */
    public SearchEventsOptionalParams setProximityId(String proximityId) {
      this.proximityId = proximityId;
      return this;
    }

    /**
     * getter for totalHits - When set, the response will include a `total_hits` property with a count of total query matches across all pages, up to the specified limit.
     */
    public Long getTotalHits() {
      return totalHits;
    }

    /**
     * setter for totalHits - When set, the response will include a `total_hits` property with a count of total query matches across all pages, up to the specified limit.
     */
    public SearchEventsOptionalParams setTotalHits(Long totalHits) {
      this.totalHits = totalHits;
      return this;
    }

    /**
     * getter for torNode - Filter events by Tor Node detection result. > Note: When using this parameter, only events with the `tor_node` property set to `true` or `false` are returned. Events without a `tor_node` detection result are left out of the response.
     */
    public Boolean getTorNode() {
      return torNode;
    }

    /**
     * setter for torNode - Filter events by Tor Node detection result. > Note: When using this parameter, only events with the `tor_node` property set to `true` or `false` are returned. Events without a `tor_node` detection result are left out of the response.
     */
    public SearchEventsOptionalParams setTorNode(Boolean torNode) {
      this.torNode = torNode;
      return this;
    }

    /**
     * getter for incrementalIdentificationStatus - Filter events by their incremental identification status (`incremental_identification_status` property). Non incremental identification events are left out of the response.
     */
    public SearchEventsIncrementalIdentificationStatus getIncrementalIdentificationStatus() {
      return incrementalIdentificationStatus;
    }

    /**
     * setter for incrementalIdentificationStatus - Filter events by their incremental identification status (`incremental_identification_status` property). Non incremental identification events are left out of the response.
     */
    public SearchEventsOptionalParams setIncrementalIdentificationStatus(
        SearchEventsIncrementalIdentificationStatus incrementalIdentificationStatus) {
      this.incrementalIdentificationStatus = incrementalIdentificationStatus;
      return this;
    }

    /**
     * getter for simulator - Filter events by iOS Simulator Detection result.  > Note: When using this parameter, only events with the `simulator` property set to `true` or `false` are returned. Events without a `simulator` Smart Signal result are left out of the response.
     */
    public Boolean getSimulator() {
      return simulator;
    }

    /**
     * setter for simulator - Filter events by iOS Simulator Detection result.  > Note: When using this parameter, only events with the `simulator` property set to `true` or `false` are returned. Events without a `simulator` Smart Signal result are left out of the response.
     */
    public SearchEventsOptionalParams setSimulator(Boolean simulator) {
      this.simulator = simulator;
      return this;
    }

    /**
     * getter for source - Selects the source of events to search. When omitted, only traditional identification events generated from devices are returned (the default behavior). When set to `edge`, only Automation Intelligence (Edge) events are returned.  > Note: The Automation Intelligence API is in public preview testing phase.  If you encounter any issues, please [contact](https://fingerprint.com/support/) our support team.
     */
    public List<SearchEventsSource> getSource() {
      return source;
    }

    /**
     * setter for source - Selects the source of events to search. When omitted, only traditional identification events generated from devices are returned (the default behavior). When set to `edge`, only Automation Intelligence (Edge) events are returned.  > Note: The Automation Intelligence API is in public preview testing phase.  If you encounter any issues, please [contact](https://fingerprint.com/support/) our support team.
     */
    public SearchEventsOptionalParams setSource(List<SearchEventsSource> source) {
      this.source = source;
      return this;
    }
  }

  /**
   * Search events
   * ## Search  The `/v4/events` endpoint provides a convenient way to search for past events based on specific parameters. Typical use cases and queries include:  - Searching for events associated with a single `visitor_id` within a time range to get historical behavior of a visitor. - Searching for events associated with a single `linked_id` within a time range to get all events associated with your internal account identifier. - Excluding all bot traffic from the query (`good` and `bad` bots)  By default, the API searches events from the last 7 days, sorts them by newest first and returns the last 10 events.  - Use `start` and `end` to specify the time range of the search. - Use `reverse=true` to sort the results oldest first. - Use `limit` to specify the number of events to return. - Use `pagination_key` to get the next page of results if there are more than `limit` events.  ### Filtering events with the `suspect` flag  The `/v4/events` endpoint unlocks a powerful method for fraud protection analytics. The `suspect` flag is exposed in all events where it was previously set by the update API.  You can also apply the `suspect` query parameter as a filter to find all potentially fraudulent activity that you previously marked as `suspect`. This helps identify patterns of fraudulent behavior.  ### Environment scoping  If you use a secret key that is scoped to an environment, you will only get events associated with the same environment. With a workspace-scoped environment, you will get events from all environments.  Smart Signals not activated for your workspace or are not included in the response.
   * @param searchEventsOptionalParams Object containing optional parameters for API method.  (optional)
   * @return EventSearch
   * @throws ApiException if fails to make API call
   * @http.response.details
   * <table summary="Response Details" border="1">
   * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
   * <tr><td> 200 </td><td> Events matching the filter(s). </td><td>  -  </td></tr>
   * <tr><td> 400 </td><td> Bad request. One or more supplied search parameters are invalid, or a required parameter is missing. </td><td>  -  </td></tr>
   * <tr><td> 403 </td><td> Forbidden. Access to this API is denied. </td><td>  -  </td></tr>
   * <tr><td> 404 </td><td> Not found. The requested visitor does not exist in this workspace&#39;s data. </td><td>  -  </td></tr>
   * <tr><td> 500 </td><td> Workspace error. </td><td>  -  </td></tr>
   * </table>
   */
  public EventSearch searchEvents(SearchEventsOptionalParams searchEventsOptionalParams)
      throws ApiException {
    return searchEventsWithHttpInfo(searchEventsOptionalParams).getData();
  }

  /**
   * Search events
   * ## Search  The `/v4/events` endpoint provides a convenient way to search for past events based on specific parameters. Typical use cases and queries include:  - Searching for events associated with a single `visitor_id` within a time range to get historical behavior of a visitor. - Searching for events associated with a single `linked_id` within a time range to get all events associated with your internal account identifier. - Excluding all bot traffic from the query (`good` and `bad` bots)  By default, the API searches events from the last 7 days, sorts them by newest first and returns the last 10 events.  - Use `start` and `end` to specify the time range of the search. - Use `reverse=true` to sort the results oldest first. - Use `limit` to specify the number of events to return. - Use `pagination_key` to get the next page of results if there are more than `limit` events.  ### Filtering events with the `suspect` flag  The `/v4/events` endpoint unlocks a powerful method for fraud protection analytics. The `suspect` flag is exposed in all events where it was previously set by the update API.  You can also apply the `suspect` query parameter as a filter to find all potentially fraudulent activity that you previously marked as `suspect`. This helps identify patterns of fraudulent behavior.  ### Environment scoping  If you use a secret key that is scoped to an environment, you will only get events associated with the same environment. With a workspace-scoped environment, you will get events from all environments.  Smart Signals not activated for your workspace or are not included in the response.
   * @param searchEventsOptionalParams Object containing optional parameters for API method.  (optional)
   * @return ApiResponse<EventSearch>
   * @throws ApiException if fails to make API call
   * @http.response.details
   * <table summary="Response Details" border="1">
   * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
   * <tr><td> 200 </td><td> Events matching the filter(s). </td><td>  -  </td></tr>
   * <tr><td> 400 </td><td> Bad request. One or more supplied search parameters are invalid, or a required parameter is missing. </td><td>  -  </td></tr>
   * <tr><td> 403 </td><td> Forbidden. Access to this API is denied. </td><td>  -  </td></tr>
   * <tr><td> 404 </td><td> Not found. The requested visitor does not exist in this workspace&#39;s data. </td><td>  -  </td></tr>
   * <tr><td> 500 </td><td> Workspace error. </td><td>  -  </td></tr>
   * </table>
   */
  public ApiResponse<EventSearch> searchEventsWithHttpInfo(
      SearchEventsOptionalParams searchEventsOptionalParams) throws ApiException {
    Object localVarPostBody = null;

    // create path and map variables
    String localVarPath = "/events";

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, String> localVarCookieParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.add(new Pair("ii", INTEGRATION_INFO));
    if (searchEventsOptionalParams != null) {
      localVarQueryParams.addAll(
          apiClient.parameterToPairs("", "limit", searchEventsOptionalParams.getLimit()));
      localVarQueryParams.addAll(
          apiClient.parameterToPairs(
              "", "pagination_key", searchEventsOptionalParams.getPaginationKey()));
      localVarQueryParams.addAll(
          apiClient.parameterToPairs("", "visitor_id", searchEventsOptionalParams.getVisitorId()));
      localVarQueryParams.addAll(
          apiClient.parameterToPairs(
              "", "high_recall_id", searchEventsOptionalParams.getHighRecallId()));
      localVarQueryParams.addAll(
          apiClient.parameterToPairs("", "bot", searchEventsOptionalParams.getBot()));
      localVarQueryParams.addAll(
          apiClient.parameterToPairs("", "bot_info", searchEventsOptionalParams.getBotInfo()));
      localVarQueryParams.addAll(
          apiClient.parameterToPairs(
              "multi", "bot_info_category", searchEventsOptionalParams.getBotInfoCategory()));
      localVarQueryParams.addAll(
          apiClient.parameterToPairs(
              "multi", "bot_info_identity", searchEventsOptionalParams.getBotInfoIdentity()));
      localVarQueryParams.addAll(
          apiClient.parameterToPairs(
              "multi", "bot_info_confidence", searchEventsOptionalParams.getBotInfoConfidence()));
      localVarQueryParams.addAll(
          apiClient.parameterToPairs(
              "multi", "bot_info_provider", searchEventsOptionalParams.getBotInfoProvider()));
      localVarQueryParams.addAll(
          apiClient.parameterToPairs(
              "multi", "bot_info_name", searchEventsOptionalParams.getBotInfoName()));
      localVarQueryParams.addAll(
          apiClient.parameterToPairs("", "ip_address", searchEventsOptionalParams.getIpAddress()));
      localVarQueryParams.addAll(
          apiClient.parameterToPairs("", "asn", searchEventsOptionalParams.getAsn()));
      localVarQueryParams.addAll(
          apiClient.parameterToPairs("", "linked_id", searchEventsOptionalParams.getLinkedId()));
      localVarQueryParams.addAll(
          apiClient.parameterToPairs("", "url", searchEventsOptionalParams.getUrl()));
      localVarQueryParams.addAll(
          apiClient.parameterToPairs("", "bundle_id", searchEventsOptionalParams.getBundleId()));
      localVarQueryParams.addAll(
          apiClient.parameterToPairs(
              "", "package_name", searchEventsOptionalParams.getPackageName()));
      localVarQueryParams.addAll(
          apiClient.parameterToPairs("", "origin", searchEventsOptionalParams.getOrigin()));
      localVarQueryParams.addAll(
          apiClient.parameterToPairs("", "start", searchEventsOptionalParams.getStart()));
      localVarQueryParams.addAll(
          apiClient.parameterToPairs("", "start", searchEventsOptionalParams.getStartDateTime()));
      localVarQueryParams.addAll(
          apiClient.parameterToPairs("", "end", searchEventsOptionalParams.getEnd()));
      localVarQueryParams.addAll(
          apiClient.parameterToPairs("", "end", searchEventsOptionalParams.getEndDateTime()));
      localVarQueryParams.addAll(
          apiClient.parameterToPairs("", "reverse", searchEventsOptionalParams.getReverse()));
      localVarQueryParams.addAll(
          apiClient.parameterToPairs("", "suspect", searchEventsOptionalParams.getSuspect()));
      localVarQueryParams.addAll(
          apiClient.parameterToPairs("", "vpn", searchEventsOptionalParams.getVpn()));
      localVarQueryParams.addAll(
          apiClient.parameterToPairs(
              "", "virtual_machine", searchEventsOptionalParams.getVirtualMachine()));
      localVarQueryParams.addAll(
          apiClient.parameterToPairs("", "tampering", searchEventsOptionalParams.getTampering()));
      localVarQueryParams.addAll(
          apiClient.parameterToPairs(
              "", "anti_detect_browser", searchEventsOptionalParams.getAntiDetectBrowser()));
      localVarQueryParams.addAll(
          apiClient.parameterToPairs("", "incognito", searchEventsOptionalParams.getIncognito()));
      localVarQueryParams.addAll(
          apiClient.parameterToPairs(
              "", "privacy_settings", searchEventsOptionalParams.getPrivacySettings()));
      localVarQueryParams.addAll(
          apiClient.parameterToPairs("", "jailbroken", searchEventsOptionalParams.getJailbroken()));
      localVarQueryParams.addAll(
          apiClient.parameterToPairs("", "frida", searchEventsOptionalParams.getFrida()));
      localVarQueryParams.addAll(
          apiClient.parameterToPairs(
              "", "factory_reset", searchEventsOptionalParams.getFactoryReset()));
      localVarQueryParams.addAll(
          apiClient.parameterToPairs("", "cloned_app", searchEventsOptionalParams.getClonedApp()));
      localVarQueryParams.addAll(
          apiClient.parameterToPairs("", "emulator", searchEventsOptionalParams.getEmulator()));
      localVarQueryParams.addAll(
          apiClient.parameterToPairs("", "root_apps", searchEventsOptionalParams.getRootApps()));
      localVarQueryParams.addAll(
          apiClient.parameterToPairs(
              "", "vpn_confidence", searchEventsOptionalParams.getVpnConfidence()));
      localVarQueryParams.addAll(
          apiClient.parameterToPairs(
              "", "min_suspect_score", searchEventsOptionalParams.getMinSuspectScore()));
      localVarQueryParams.addAll(
          apiClient.parameterToPairs(
              "", "developer_tools", searchEventsOptionalParams.getDeveloperTools()));
      localVarQueryParams.addAll(
          apiClient.parameterToPairs(
              "", "location_spoofing", searchEventsOptionalParams.getLocationSpoofing()));
      localVarQueryParams.addAll(
          apiClient.parameterToPairs(
              "", "mitm_attack", searchEventsOptionalParams.getMitmAttack()));
      localVarQueryParams.addAll(
          apiClient.parameterToPairs(
              "", "rare_device", searchEventsOptionalParams.getRareDevice()));
      localVarQueryParams.addAll(
          apiClient.parameterToPairs(
              "",
              "rare_device_percentile_bucket",
              searchEventsOptionalParams.getRareDevicePercentileBucket()));
      localVarQueryParams.addAll(
          apiClient.parameterToPairs("", "proxy", searchEventsOptionalParams.getProxy()));
      localVarQueryParams.addAll(
          apiClient.parameterToPairs(
              "", "sdk_version", searchEventsOptionalParams.getSdkVersion()));
      localVarQueryParams.addAll(
          apiClient.parameterToPairs(
              "", "sdk_platform", searchEventsOptionalParams.getSdkPlatform()));
      localVarQueryParams.addAll(
          apiClient.parameterToPairs(
              "multi", "environment", searchEventsOptionalParams.getEnvironment()));
      localVarQueryParams.addAll(
          apiClient.parameterToPairs(
              "", "proximity_id", searchEventsOptionalParams.getProximityId()));
      localVarQueryParams.addAll(
          apiClient.parameterToPairs("", "total_hits", searchEventsOptionalParams.getTotalHits()));
      localVarQueryParams.addAll(
          apiClient.parameterToPairs("", "tor_node", searchEventsOptionalParams.getTorNode()));
      localVarQueryParams.addAll(
          apiClient.parameterToPairs(
              "",
              "incremental_identification_status",
              searchEventsOptionalParams.getIncrementalIdentificationStatus()));
      localVarQueryParams.addAll(
          apiClient.parameterToPairs("", "simulator", searchEventsOptionalParams.getSimulator()));
      localVarQueryParams.addAll(
          apiClient.parameterToPairs("multi", "source", searchEventsOptionalParams.getSource()));
    }

    final String localVarAccept = apiClient.selectHeaderAccept("application/json");
    final String localVarContentType = apiClient.selectHeaderContentType();
    String[] localVarAuthNames = new String[] {"bearerAuth"};

    GenericType<EventSearch> localVarReturnType = new GenericType<EventSearch>() {};

    return apiClient.invokeAPI(
        "FingerprintApi.searchEvents",
        localVarPath,
        "GET",
        localVarQueryParams,
        localVarPostBody,
        localVarHeaderParams,
        localVarCookieParams,
        localVarFormParams,
        localVarAccept,
        localVarContentType,
        localVarAuthNames,
        localVarReturnType,
        false);
  }

  /**
   * Update an event
   * Change information in existing events specified by `event_id` or *flag suspicious events*.  When an event is created, it can be assigned `linked_id` and `tags` submitted through the JS agent parameters.  This information might not have been available on the client initially, so the Server API permits updating these attributes after the fact.  **Warning** It's not possible to update events older than one month.   **Warning** Trying to update an event immediately after creation may temporarily result in an  error (HTTP 409 Conflict. The event is not mutable yet.) as the event is fully propagated across our systems. In such a case, simply retry the request.
   * @param eventId The unique event [identifier](https://docs.fingerprint.com/reference/js-agent-v4-get-function#event_id). (required)
   * @param eventUpdate  (required)
   * @throws ApiException if fails to make API call
   * @http.response.details
   * <table summary="Response Details" border="1">
   * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
   * <tr><td> 200 </td><td> OK. </td><td>  -  </td></tr>
   * <tr><td> 400 </td><td> Bad request. The request payload is not valid. </td><td>  -  </td></tr>
   * <tr><td> 403 </td><td> Forbidden. Access to this API is denied. </td><td>  -  </td></tr>
   * <tr><td> 404 </td><td> Not found. The event Id cannot be found in this workspace&#39;s data. </td><td>  -  </td></tr>
   * <tr><td> 409 </td><td> Conflict. The event is not mutable yet. </td><td>  -  </td></tr>
   * </table>
   */
  public void updateEvent(String eventId, EventUpdate eventUpdate) throws ApiException {
    updateEventWithHttpInfo(eventId, eventUpdate);
  }

  /**
   * Update an event
   * Change information in existing events specified by `event_id` or *flag suspicious events*.  When an event is created, it can be assigned `linked_id` and `tags` submitted through the JS agent parameters.  This information might not have been available on the client initially, so the Server API permits updating these attributes after the fact.  **Warning** It's not possible to update events older than one month.   **Warning** Trying to update an event immediately after creation may temporarily result in an  error (HTTP 409 Conflict. The event is not mutable yet.) as the event is fully propagated across our systems. In such a case, simply retry the request.
   * @param eventId The unique event [identifier](https://docs.fingerprint.com/reference/js-agent-v4-get-function#event_id). (required)
   * @param eventUpdate  (required)
   * @return ApiResponse<Void>
   * @throws ApiException if fails to make API call
   * @http.response.details
   * <table summary="Response Details" border="1">
   * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
   * <tr><td> 200 </td><td> OK. </td><td>  -  </td></tr>
   * <tr><td> 400 </td><td> Bad request. The request payload is not valid. </td><td>  -  </td></tr>
   * <tr><td> 403 </td><td> Forbidden. Access to this API is denied. </td><td>  -  </td></tr>
   * <tr><td> 404 </td><td> Not found. The event Id cannot be found in this workspace&#39;s data. </td><td>  -  </td></tr>
   * <tr><td> 409 </td><td> Conflict. The event is not mutable yet. </td><td>  -  </td></tr>
   * </table>
   */
  public ApiResponse<Void> updateEventWithHttpInfo(String eventId, EventUpdate eventUpdate)
      throws ApiException {
    Object localVarPostBody = eventUpdate;

    // verify the required parameter 'eventId' is set
    if (eventId == null) {
      throw new ApiException(
          400, "Missing the required parameter 'eventId' when calling updateEvent");
    }

    // verify the required parameter 'eventUpdate' is set
    if (eventUpdate == null) {
      throw new ApiException(
          400, "Missing the required parameter 'eventUpdate' when calling updateEvent");
    }

    // create path and map variables
    String localVarPath =
        "/events/{event_id}"
            .replaceAll("\\{" + "event_id" + "\\}", apiClient.escapeString(eventId.toString()));

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, String> localVarCookieParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.add(new Pair("ii", INTEGRATION_INFO));

    final String localVarAccept = apiClient.selectHeaderAccept("application/json");
    final String localVarContentType = apiClient.selectHeaderContentType("application/json");
    String[] localVarAuthNames = new String[] {"bearerAuth"};

    return apiClient.invokeAPI(
        "FingerprintApi.updateEvent",
        localVarPath,
        "PATCH",
        localVarQueryParams,
        localVarPostBody,
        localVarHeaderParams,
        localVarCookieParams,
        localVarFormParams,
        localVarAccept,
        localVarContentType,
        localVarAuthNames,
        null,
        false);
  }
}
