#!/usr/bin/env bash

set -ex

curl -s -o ./res/fingerprint-server-api.yaml https://fingerprintjs.github.io/fingerprint-pro-server-api-openapi/schemas/fingerprint-server-api-v4.yaml

examplesList=(
  'webhook/webhook_event.json'
  'events/get_event_200.json'
  'events/search/get_event_search_200.json'
  'events/update_event_multiple_fields_request.json'
  'events/update_event_one_field_request.json'
  'errors/400_bot_type_invalid.json'
  'errors/400_end_time_invalid.json'
  'errors/400_event_id_invalid.json'
  'errors/400_ip_address_invalid.json'
  'errors/400_limit_invalid.json'
  'errors/400_linked_id_invalid.json'
  'errors/400_pagination_key_invalid.json'
  'errors/400_request_body_invalid.json'
  'errors/400_reverse_invalid.json'
  'errors/400_start_time_invalid.json'
  'errors/400_visitor_id_invalid.json'
  'errors/400_visitor_id_required.json'
  'errors/403_feature_not_enabled.json'
  'errors/403_secret_api_key_not_found.json'
  'errors/403_secret_api_key_required.json'
  'errors/403_subscription_not_active.json'
  'errors/403_wrong_region.json'
  'errors/404_event_not_found.json'
  'errors/404_visitor_not_found.json'
  'errors/409_state_not_ready.json'
  'errors/429_too_many_requests.json'
  'errors/500_internal_server_error.json'
)

for example in "${examplesList[@]}"; do
  curl --create-dirs -s -o ./sdk/src/test/resources/mocks/"$example" https://fingerprintjs.github.io/fingerprint-pro-server-api-openapi/examples/"$example"
done