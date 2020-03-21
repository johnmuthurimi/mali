FROM openjdk:8-jre-alpine
ENV APP_FILE discovery-service-1.0.jar
ENV APP_HOME /app
EXPOSE 5002
COPY target/$APP_FILE $APP_HOME/
COPY wait-for-it.sh $APP_HOME/
RUN chmod +x $APP_HOME/wait-for-it.sh
WORKDIR $APP_HOME
ENTRYPOINT ["sh", "-c"]
CMD ["exec java -jar $APP_FILE"]