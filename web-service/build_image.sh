
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

rm -rf dist/*.js
ng build --prod

docker build -t mucunga90/web-service:latest -f $DIR/Dockerfile $DIR