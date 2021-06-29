FROM adoptopenjdk/openjdk11
ENV LANG='en_US.UTF-8' LANGUAGE='en_US:en' LC_ALL='en_US.UTF-8'
RUN /bin/mkdir -p /app/mutants/config
RUN /bin/mkdir -p /app/mutants/schemas
ADD api.jar /app/muntants/app.jar
ADD application.yaml /app/mutants/config/
ENV JAVA_OPTS="-XX:+UseContainerSupport -Dfile.encoding=UTF8 -Dspring.config.location=file:/home/mutants/config/application.yaml"
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /app/mutants/app.jar"]

