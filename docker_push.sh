#!/bin/bash

# Build
cd techvideo
./mvnw install dockerfile:build

# Push
echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin
docker push nwidart/techvideo
