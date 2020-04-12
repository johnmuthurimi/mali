
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

rm -rf dist/*.js
ng build --prod

docker build -f $DIR/Dockerfile $DIR -t mucunga90/web-service:1.0.0 --rm=true
