FROM openjdk:16-jdk-alpine
EXPOSE 8555
ADD target/tr069-device-service-0.0.1-SNAPSHOT.jar tr069-device-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "tr069-device-service-0.0.1-SNAPSHOT.jar"]