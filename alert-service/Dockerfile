FROM java:8

EXPOSE 5002

MAINTAINER John Peter <mucunga90@gmail.com>

ADD target/alert-service*.jar app.jar

RUN bash -c 'touch /app.jar'

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]