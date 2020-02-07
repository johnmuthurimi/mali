#!/usr/bin/env bash

# get the absolute path of the executable
SELF_PATH=$(cd -P -- "$(dirname -- "$0")" && pwd -P) && SELF_PATH="$SELF_PATH"/$(basename -- "$0")

echo "Stopping all services"
docker ps | \
grep "consul-service\|rabbitmq-service\|mysql-service\|gateway-service\|user-service\|alert-service" | \
awk '{print $1}' | xargs docker stop

# Allow comment in PROD
# echo "Pruning all the services"
# remove all images, containers, volumes and networks
#docker system prune -a

echo -n "Build new images? y/n  "
read build_images
if [ "$build_images" == "y" ]; then
	# Included service here if you want to build the docker image
	echo "Generating gateway-service image..."
	cd ./../gateway-service/
	sh ./build_image.sh

	echo "Generating user-service image..."
	cd ./../user-service/
	sh ./build_image.sh
fi

#Allow comment in PROD
if [ "$build_images" == "y" ]; then
	echo -n "Docker credentials for publishing images"	
	docker login
	# Included service here if you want to publish this to docker hub
	#docker push mucunga90/gateway-service:latest
	#docker push mucunga90/user-service:latest
fi

echo "Starting your local dockerized full stack with mounted volumes"
cd ./../docker/
docker-compose -f docker-compose-files/full-stack.yml up -d