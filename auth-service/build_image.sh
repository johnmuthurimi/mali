
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

mvn clean install
rm -rf target/*.jar
mvn package spring-boot:repackage

docker build -t mucunga90/auth-service:latest -f $DIR/Dockerfile $DIR