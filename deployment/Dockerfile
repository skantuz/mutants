FROM adoptopenjdk/openjdk11:alpine-slim
ENV LANG='en_US.UTF-8' LANGUAGE='en_US:en' LC_ALL='en_US.UTF-8'
RUN /bin/mkdir -p /app/config
ADD *.jar /app/app.jar
ADD application.yaml /app/config/application.yaml
ADD mutant_schema.json /app/config/mutant_schema.json
ENV JAVA_OPTS="-XX:+UseContainerSupport -Dfile.encoding=UTF8 -Dspring.config.location=file:/app/config/application.yaml"
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /app/app.jar"]

