#!/usr/bin/env bash

# get the absolute path of the executable
SELF_PATH=$(cd -P -- "$(dirname -- "$0")" && pwd -P) && SELF_PATH="$SELF_PATH"/$(basename -- "$0")

echo "Take down the stack (application)"
docker stack rm dev

echo "Take down the Swarm cluster"
docker swarm leave --force

echo "System prune all images, containers and volumes"
echo "Remove unneccessary images and containers"
docker rm $(docker ps -a -f status=exited -q)
docker rmi $(docker images -f "dangling=true" -q)

echo -n "Build new images? y/n  "
read build_images
if [ "$build_images" == "y" ]; then

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
	#docker push mucunga90/gateway-service:latest
	#docker push mucunga90/user-service:latest
	#docker push mucunga90/alert-service:latest
	#docker push mucunga90/web-service:latest
fi

echo "Starting your local dockerized full stack with mounted volumes"
cd ./../docker/

echo "Create Swarm Cluster"
docker swarm init

# update which environment
echo -n "Build which environment: [dev/prod]  "
read build_env

echo "Deploy application services"

docker service create --replicas 1 --name prometheus-service \
    --mount type=bind,source=/home/muthurimi/workspace/mali/docker/dev/prometheus.yml,destination=/etc/prometheus/prometheus.yml \
    --publish published=9090,target=9090,protocol=tcp \
    prom/prometheus

docker stack deploy --compose-file $build_env/docker-compose.yml $build_env

echo "Check the Status of the Running Services on a Swarm Cluster"
docker service ls
