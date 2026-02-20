#!/usr/bin/env bash

# Change the package.json version
pnpm exec changeset version && \

# Change the gradle version
bash ./scripts/update-gradle-version.sh && \

# Re-generate the code with the updated version
bash ./scripts/generate.sh
