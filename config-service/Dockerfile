FROM openjdk:8-jdk-alpine
COPY target/config-service*.jar /app/app.jar
CMD ["java", "-jar", "/app/app.jar"]
EXPOSE 8888