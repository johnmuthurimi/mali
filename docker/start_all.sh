#!/usr/bin/env bash

# get the absolute path of the executable
SELF_PATH=$(cd -P -- "$(dirname -- "$0")" && pwd -P) && SELF_PATH="$SELF_PATH"/$(basename -- "$0")

# remove all images, containers, volumes and networks
echo "Pruning all the services"
docker system prune -a --volumes

echo "Stopping all services"
docker ps | \
grep "rabbitmq-service\|mysql-service\|config-service\|discovery-service\|gateway-service\|proxy-service\|user-service" | \
awk '{print $1}' | xargs docker stop

echo -n "Build new images? y/n  "
read build_images
if [ "$build_images" == "y" ]; then

	# Included service here if you want to build the docker image
	echo "Generating config-service image..."
	cd ./../config-service/
	sh ./build_image.sh

	# Included service here if you want to build the docker image
	echo "Generating discovery-service image..."
	cd ./../discovery-service/
	sh ./build_image.sh

	# Included service here if you want to build the docker image
	echo "Generating gateway-service image..."
	cd ./../gateway-service/
	sh ./build_image.sh

	# Included service here if you want to build the docker image
	echo "Generating proxy-service image..."
	cd ./../proxy-service/
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
	#docker push mucunga90/config-service:latest
	#docker push mucunga90/discovery-service:latest
	#docker push mucunga90/gateway-service:latest
	#docker push mucunga90/proxy-service:latest
	#docker push mucunga90/user-service:latest
fi

echo "Starting your local dockerized full stack with mounted volumes"
cd ./../docker/

# update which environment
echo -n "Build which environment: dev/test/live  "
read build_env
if [ "$build_env" == "dev" ]; then
	docker-compose -f ${build_env}/docker-compose.yml up -d
fi


