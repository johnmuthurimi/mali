#!/usr/bin/env bash

# get the absolute path of the executable
SELF_PATH=$(cd -P -- "$(dirname -- "$0")" && pwd -P) && SELF_PATH="$SELF_PATH"/$(basename -- "$0")

printf "\nTake down the stack (application)\n===================\n"
docker stack rm dev

printf "\nTake down the Swarm cluster\n===================\n"
docker swarm leave --force

printf "\nSystem prune all images, containers and volumes\n===================\n"
docker container rm $(docker ps -f "status=exited" -q)
docker stop $(docker ps -a)
docker rmi $(docker image ls -f "dangling=true" -q)

printf "\n\n"
echo -n "Build new images? y/n  "
read build_images
if [ "$build_images" == "y" ]; then

	# Included service here if you want to build the docker image
	printf "\nGenerating gateway-service image...\n======================================\\n"
	cd ./../gateway-service/
	sh ./build_image.sh

	# Included service here if you want to build the docker image
	printf "\nGenerating core-service image...\n======================================\\n"
	cd ./../core-service/
	sh ./build_image.sh
	
	# Included service here if you want to build the docker image
	printf "\nGenerating user-service image...\n======================================\\n"
	cd ./../user-service/
	sh ./build_image.sh

	# Included service here if you want to build the docker image
	printf "\nGenerating alert-service image...\n======================================\\n"
	cd ./../alert-service/
	sh ./build_image.sh


	# Included service here if you want to build the docker image
	printf "\nGenerating web-service image...\n======================================\\n"
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

printf "\nStarting your local dockerized full stack with mounted volumes\n============================================================================\n"
cd ./../docker/

# update which environment
echo -n "Build which environment: [dev/prod]  "
read build_env

#https://codefresh.io/docker-tutorial/deploy-docker-compose-v3-swarm-mode-cluster/
printf "\nCreate Swarm Cluster\n===================\n"
# vars
[ -z "$NUM_WORKERS" ] && NUM_WORKERS=3

# init swarm (need for service command); if not created
docker node ls 2> /dev/null | grep "Leader"
if [ $? -ne 0 ]; then
  docker swarm init > /dev/null 2>&1
fi

# get join token
SWARM_TOKEN=$(docker swarm join-token -q worker)

# get Swarm master IP (Docker for Mac xhyve VM IP)
SWARM_MASTER=$(docker info --format "{{.Swarm.NodeAddr}}")
echo "Swarm master IP: ${SWARM_MASTER}"
sleep 10

printf "\nstart Docker registry mirror\n===================\n"
# start Docker registry mirror
docker run -d --restart=always -p 4000:5000 --name v2_mirror \
  -v $PWD/docker-registry:/var/lib/registry \
  -e REGISTRY_PROXY_REMOTEURL=https://registry-1.docker.io \
  registry:2.5

printf "\nrun NUM_WORKERS workers with SWARM_TOKEN\n===================\n"
# run NUM_WORKERS workers with SWARM_TOKEN
for i in $(seq "${NUM_WORKERS}"); do
  # remove node from cluster if exists
  docker node rm --force $(docker node ls --filter "name=worker-${i}" -q) > /dev/null 2>&1
  # remove worker contianer with same name if exists
  docker rm --force $(docker ps -q --filter "name=worker-${i}") > /dev/null 2>&1
  # run new worker container
  docker run -d --privileged --name worker-${i} --hostname=worker-${i} \
    -p ${i}2375:2375 \
    -p ${i}8500:8500 \
    -p ${i}5672:5672 \
    -p ${i}15672:15672 \
    -p ${i}3308:3308 \
    -p ${i}80:80 \
    -p ${i}8080:8080 \
    -p ${i}5001:5001 \
    -p ${i}5002:5002 \
    docker:1.13-rc-dind --registry-mirror http://${SWARM_MASTER}:4000
  # add worker container to the cluster
  docker --host=localhost:${i}2375 swarm join --token ${SWARM_TOKEN} ${SWARM_MASTER}:2377
done

# show swarm cluster
printf "\nLocal Swarm Cluster\n===================\n"
docker node ls

printf "\nDeploy application services\n===================\n"
docker stack deploy -c $build_env/docker-compose.yml $build_env

# echo swarm visualizer
printf "\nLocal Swarm Visualizer\n===================\n"
docker run -it -d --name swarm_visualizer \
  -p 8000:8080 -e HOST=localhost \
  -v /var/run/docker.sock:/var/run/docker.sock \
  dockersamples/visualizer

printf "\nCheck the Status of the Running Services on a Swarm Cluster\n===================\n"
docker service ls
