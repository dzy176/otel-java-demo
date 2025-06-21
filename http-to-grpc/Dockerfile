FROM openjdk:17-jdk
LABEL authors="dzy"

COPY target/http-to-grpc-0.0.1-SNAPSHOT.jar /app/http-to-grpc.jar

ENTRYPOINT ["java", "-Xms128m", "-Xmx128m", "-jar", "/app/http-to-grpc.jar"]