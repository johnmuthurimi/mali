#!/usr/bin/env bash

# get the absolute path of the executable
SELF_PATH=$(cd -P -- "$(dirname -- "$0")" && pwd -P) && SELF_PATH="$SELF_PATH"/$(basename -- "$0")

echo "Stopping all"
docker ps | \
grep "consul-service\|rabbitmq-service\|gateway-service\|auth-service\|email-service" | \
awk '{print $1}' | xargs docker stop

echo -n "Build new images? y/n  "
read build_images
if [ "$build_images" == "y" ]; then
	echo "Generating gateway-service image..."
	cd ./../gateway-service/
	sh ./build_image.sh
	
	echo "Generating auth-service image..."
	cd ./../auth-service/
	sh ./build_image.sh
	
	echo "Generating email-service image..."
	cd ./../email-service/
	sh ./build_image.sh
fi

if [ "$build_images" == "y" ]; then
	echo -n "Docker credentials for publishing images"
	#docker login
	docker images
	#docker push mucunga90/gateway-service:latest
	#docker push mucunga90/auth-service:latest
	#docker push mucunga90/email-service:latest
fi

#echo "Starting your local dockerized full stack with mounted volumes"
cd ./../docker/
docker-compose -f docker-compose-files/full-stack.yml up -d