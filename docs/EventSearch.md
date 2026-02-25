

# EventSearch

Contains a list of all identification events matching the specified search criteria.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**events** | [**List&lt;Event&gt;**](Event.md) |  |  |
|**paginationKey** | **String** | Use this value in the `pagination_key` parameter to request the next page of search results. |  [optional] |
|**totalHits** | **Long** | This value represents the total number of events matching the search query, up to the limit provided in the `total_hits` query parameter. Only present if the `total_hits` query parameter was provided. |  [optional] |



