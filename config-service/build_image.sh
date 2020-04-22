
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

rm -rf target/*.jar
mvn clean package spring-boot:repackage

docker image build -f $DIR/Dockerfile $DIR -t mucunga90/config-service:1.0.1 --rm=true