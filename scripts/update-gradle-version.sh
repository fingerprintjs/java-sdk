#!/usr/bin/env bash

VERSION=$(jq -r '.version' package.json)

echo "VERSION: $VERSION"

platform=$(uname)
(
  if [ "$platform" = "Darwin" ]; then
    sed -i '' -e "s/projectVersion = .*$/projectVersion = $VERSION/g" ./gradle.properties
  else
    sed -i "s/projectVersion = .*$/projectVersion = $VERSION/g" ./gradle.properties
  fi
)
