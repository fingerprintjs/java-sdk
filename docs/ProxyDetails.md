

# ProxyDetails

Proxy detection details (present if `proxy` is `true`)

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**proxyType** | [**ProxyTypeEnum**](#ProxyTypeEnum) | Proxy type:  * `residential` - proxies that route through residential and telecom IP addresses to appear as legitimate traffic  * `data_center` - proxies which route through data centers  * `unknown` - reported when a proxy is detected solely by the ML model and the IP sources did not determine a specific type  |  |
|**lastSeenAt** | **Long** | Unix millisecond timestamp with hourly resolution of when this IP was last seen as a proxy  |  [optional] |
|**provider** | **String** | String representing the last proxy service provider detected when this IP was synced. An IP can be shared by multiple service providers.  |  [optional] |



## Enum: ProxyTypeEnum

| Name | Value |
|---- | -----|
| RESIDENTIAL | "residential" |
| DATA_CENTER | "data_center" |
| UNKNOWN | "unknown" |
| UNSUPPORTED_VALUE_SDK_UPGRADE_REQUIRED | "unsupported_value_sdk_upgrade_required" |



