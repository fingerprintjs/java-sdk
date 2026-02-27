

# ProxyDetails

Proxy detection details (present if `proxy` is `true`)

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**proxyType** | [**ProxyTypeEnum**](#ProxyTypeEnum) | Residential proxies use real user IP addresses to appear as legitimate traffic,  while data center proxies are public proxies hosted in data centers  |  |
|**lastSeenAt** | **Long** | Unix millisecond timestamp with hourly resolution of when this IP was last seen as a proxy  |  [optional] |
|**provider** | **String** | String representing the last proxy service provider detected when this IP was synced. An IP can be shared by multiple service providers.  |  [optional] |



## Enum: ProxyTypeEnum

| Name | Value |
|---- | -----|
| RESIDENTIAL | "residential" |
| DATA_CENTER | "data_center" |
| UNSUPPORTED_VALUE_SDK_UPGRADE_REQUIRED | "unsupported_value_sdk_upgrade_required" |



