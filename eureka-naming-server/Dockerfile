FROM openjdk:16-jdk-alpine
EXPOSE 6789
ADD target/eureka-naming-server-0.0.1-SNAPSHOT.jar eureka-naming-server-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "eureka-naming-server-0.0.1-SNAPSHOT.jar"]