

# EventRuleActionBlock

Informs the client the request should be blocked using the response described by this rule action.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**rulesetId** | **String** | The ID of the evaluated ruleset. |  |
|**ruleId** | **String** | The ID of the rule that matched the identification event. |  [optional] |
|**ruleExpression** | **String** | The expression of the rule that matched the identification event. |  [optional] |
|**type** | **RuleActionType** |  |  |
|**statusCode** | **Integer** | A valid HTTP status code. |  [optional] |
|**headers** | [**List&lt;RuleActionHeaderField&gt;**](RuleActionHeaderField.md) | A list of headers to send. |  [optional] |
|**body** | **String** | The response body to send to the client. |  [optional] |


## Implemented Interfaces

* EventRuleAction


