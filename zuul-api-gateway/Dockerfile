FROM openjdk:16-jdk-alpine
EXPOSE 8888
ADD target/zuul-api-gateway-0.0.1-SNAPSHOT.jar zuul-api-gateway-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "zuul-api-gateway-0.0.1-SNAPSHOT.jar"]