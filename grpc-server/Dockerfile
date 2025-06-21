FROM openjdk:17-jdk
LABEL authors="dzy"

COPY target/grpc-server-0.0.1-SNAPSHOT.jar /app/grpc-server.jar

ENTRYPOINT ["java", "-Xms256m", "-Xmx256m", "-XX:+UseZGC", "-jar", "/app/grpc-server.jar"]