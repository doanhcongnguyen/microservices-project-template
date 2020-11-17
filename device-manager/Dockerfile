FROM openjdk:16-jdk-alpine
EXPOSE 9111
ADD target/device-manager-0.0.1-SNAPSHOT.jar device-manager-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "device-manager-0.0.1-SNAPSHOT.jar"]