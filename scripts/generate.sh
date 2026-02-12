#!/usr/bin/env bash

./gradlew clean
./gradlew openApiGenerate copyClasses copyDocs copyReadme
