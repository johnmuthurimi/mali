#!/usr/bin/env bash

# get the absolute path of the executable
SELF_PATH=$(cd -P -- "$(dirname -- "$0")" && pwd -P) && SELF_PATH="$SELF_PATH"/$(basename -- "$0")

echo "Stopping all services"
docker ps | \
grep "consul-service\|rabbitmq-service\|gateway-service" | \
awk '{print $1}' | xargs docker stop

# remove all images, containers, volumes and networks
echo "Pruning all the services"
docker system prune

echo -n "Build new images? y/n  "
read build_images
if [ "$build_images" == "y" ]; then
	echo "Generating gateway-service image..."
	cd ./../gateway-service/
	sh ./build_image.sh

fi

if [ "$build_images" == "y" ]; then
	echo -n "Docker credentials for publishing images"
	#docker login
	#docker push mucunga90/gateway-service:latest
fi

#echo "Starting your local dockerized full stack with mounted volumes"
cd ./../docker/
docker-compose -f docker-compose-files/full-stack.yml up -d