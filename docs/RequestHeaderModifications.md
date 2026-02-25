

# RequestHeaderModifications

The set of header modifications to apply, in the following order: remove, set, append.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**remove** | **List&lt;String&gt;** | The list of headers to remove. |  [optional] |
|**set** | [**List&lt;RuleActionHeaderField&gt;**](RuleActionHeaderField.md) | The list of headers to set, overwriting any existing headers with the same name. |  [optional] |
|**append** | [**List&lt;RuleActionHeaderField&gt;**](RuleActionHeaderField.md) | The list of headers to append. |  [optional] |



