#!/usr/bin/env bash

echo "See Memory and CPU Usage for All Your Docker Containers"
docker ps -q | xargs  docker stats --no-stream

# get the absolute path of the executable
SELF_PATH=$(cd -P -- "$(dirname -- "$0")" && pwd -P) && SELF_PATH="$SELF_PATH"/$(basename -- "$0")

# remove all images, containers, volumes and networks
echo "Pruning all the services"
docker system prune -a --volumes

echo "Stopping all services"
docker ps | \
grep "rabbitmq-service\|mysql-service\|config-service\|discovery-service\|proxy-service\|gateway-service\|user-service\|alert-service\|web-service" | \
awk '{print $1}' | xargs docker stop

echo -n "Build new images? y/n  "
read build_images
if [ "$build_images" == "y" ]; then

	# Included service here if you want to build the docker image
	echo "Generating config-service image..."
	cd ./..
	sh ./build_image.sh
	cd ./docker

	# Included service here if you want to build the docker image
	echo "Generating config-service image..."
	cd ./../config-service/
	sh ./build_image.sh

	# Included service here if you want to build the docker image
	echo "Generating discovery-service image..."
	cd ./../discovery-service/
	sh ./build_image.sh

	# Included service here if you want to build the docker image
	echo "Generating proxy-service image..."
	cd ./../proxy-service/
	sh ./build_image.sh

	# Included service here if you want to build the docker image
	echo "Generating gateway-service image..."
	cd ./../gateway-service/
	sh ./build_image.sh

	# Included service here if you want to build the docker image
	echo "Generating core-service image..."
	cd ./../core-service/
	sh ./build_image.sh
	
	# Included service here if you want to build the docker image
	echo "Generating user-service image..."
	cd ./../user-service/
	sh ./build_image.sh

	# Included service here if you want to build the docker image
	echo "Generating alert-service image..."
	cd ./../alert-service/
	sh ./build_image.sh


	# Included service here if you want to build the docker image
	echo "Generating web-service image..."
	cd ./../web-service/
	sh ./build_image.sh
fi

#Allow comment in PROD
if [ "$build_images" == "y" ]; then
	echo -n "Docker credentials for publishing images"	
	docker login
	# Included service here if you want to publish this to docker hub
	#docker push mucunga90/config-service:latest
	#docker push mucunga90/discovery-service:latest
	#docker push mucunga90/proxy-service:latest
	#docker push mucunga90/gateway-service:latest	
	#docker push mucunga90/user-service:latest
	#docker push mucunga90/alert-service:latest
	#docker push mucunga90/web-service:latest
fi

echo "Starting your local dockerized full stack with mounted volumes"
cd ./../docker/

# update which environment
echo -n "Build which environment: [dev/prod]  "
read build_env

echo -n "Compose configuration services: [y/n]  "
read build_config
if [ "$build_config" == "y" ]; then
	docker-compose -f $build_env/docker-compose-config.yml up -d
fi

echo -n "Compose application services: [y/n]  "
read build_service
if [ "$build_service" == "y" ]; then
	docker-compose -f $build_env/docker-compose-service.yml up -d
fi

echo "See Memory and CPU Usage for All Your Docker Containers"
docker ps -q | xargs  docker stats --no-stream




