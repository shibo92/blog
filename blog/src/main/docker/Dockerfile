FROM openjdk:8-jdk-slim
VOLUME /tmp
ADD blog-0.0.1-SNAPSHOT.jar app.jar
##ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
EXPOSE 8081