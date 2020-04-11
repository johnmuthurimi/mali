
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

rm -rf target/*.jar
mvn clean package spring-boot:repackage

docker image build -t mucunga90/user-service:latest -f $DIR/Dockerfile $DIR