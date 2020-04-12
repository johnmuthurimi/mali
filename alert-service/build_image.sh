
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

rm -rf target/*.jar
mvn clean package spring-boot:repackage

docker build -f $DIR/Dockerfile $DIR -t mucunga90/alert-service:1.0.0 --rm=true