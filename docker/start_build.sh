#!/usr/bin/env bash

# get the absolute path of the executable
SELF_PATH=$(cd -P -- "$(dirname -- "$0")" && pwd -P) && SELF_PATH="$SELF_PATH"/$(basename -- "$0")

echo -n "Build new images? y/n  "
read build_images
if [ "$build_images" == "y" ]; then

  # Included service here if you want to build the docker image
	printf "\nGenerating core-service image...\n======================================\\n"
	cd ./../core-service/
	sh ./build_image.sh

  # Included service here if you want to build the docker image
	printf "\nGenerating config-service image...\n======================================\\n"
	cd ./../config-service/
	sh ./build_image.sh

	# Included service here if you want to build the docker image
	printf "\nGenerating discovery-service image...\n======================================\\n"
	cd ./../discovery-service/
	sh ./build_image.sh

	# Included service here if you want to build the docker image
	printf "\nGenerating auth-service image...\n======================================\\n"
	cd ./../auth-service/
	sh ./build_image.sh

	# Included service here if you want to build the docker image
	printf "\nGenerating gateway-service image...\n======================================\\n"
	cd ./../gateway-service/
	sh ./build_image.sh

		# Included service here if you want to build the docker image
	printf "\nGenerating monitoring-service image...\n======================================\\n"
	cd ./../monitoring-service/
	sh ./build_image.sh

  # Included service here if you want to build the docker image
	printf "\nGenerating alert-service image...\n======================================\\n"
	cd ./../alert-service/
	sh ./build_image.sh

	# Included service here if you want to build the docker image
	printf "\nGenerating user-service image...\n======================================\\n"
	cd ./../user-service/
	sh ./build_image.sh

	# Included service here if you want to build the docker image
	printf "\nGenerating web-service image...\n======================================\\n"
	cd ./../web-service/
	sh ./build_image.sh
fi

if [ "$build_images" == "y" ]; then
	echo -n "Docker credentials for publishing images"
	docker login
	# Included service here if you want to publish this to docker hub
	docker push mucunga90/config-service:1.0.0
	docker push mucunga90/discovery-service:1.0.0
	docker push mucunga90/auth-service:1.0.0
	docker push mucunga90/gateway-service:1.0.0
	docker push mucunga90/alert-service:1.0.0
	docker push mucunga90/user-service:1.0.0
	docker push mucunga90/web-service:1.0.0
fi

cd ./../docker/


