FROM adoptopenjdk/openjdk11
ENV LANG='en_US.UTF-8' LANGUAGE='en_US:en' LC_ALL='en_US.UTF-8'
RUN /bin/mkdir -p /app/config
ADD *.jar /app/app.jar
ADD application.yaml /app/config/
ADD *json /app/config/
ENV JAVA_OPTS="-XX:+UseContainerSupport -Dfile.encoding=UTF8"
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /app/app.jar"]

