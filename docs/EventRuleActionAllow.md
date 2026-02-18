

# EventRuleActionAllow

Informs the client that the request should be forwarded to the origin with optional request header modifications.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**rulesetId** | **String** | The ID of the evaluated ruleset. |  |
|**ruleId** | **String** | The ID of the rule that matched the identification event. |  [optional] |
|**ruleExpression** | **String** | The expression of the rule that matched the identification event. |  [optional] |
|**type** | **RuleActionType** |  |  |
|**requestHeaderModifications** | [**RequestHeaderModifications**](RequestHeaderModifications.md) |  |  [optional] |


## Implemented Interfaces

* EventRuleAction


