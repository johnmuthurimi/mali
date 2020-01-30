#!/usr/bin/env bash

# get the absolute path of the executable
SELF_PATH=$(cd -P -- "$(dirname -- "$0")" && pwd -P) && SELF_PATH="$SELF_PATH"/$(basename -- "$0")

echo "Stopping all"
docker ps | \
grep "consul-service\|rabbitmq-service\|mysql-service\|gateway-service\|user-service\|alert-service" | \
awk '{print $1}' | xargs docker stop