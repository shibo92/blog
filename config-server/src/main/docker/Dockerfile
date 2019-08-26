FROM openjdk:8-jdk-slim
VOLUME /tmp
ADD config-server-0.0.1-SNAPSHOT.jar app.jar
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
EXPOSE 8080