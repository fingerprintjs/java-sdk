#!/usr/bin/env bash
pnpm exec changeset version && bash ./scripts/update-gradle-version.sh
