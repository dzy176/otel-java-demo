FROM openjdk:17-jdk
LABEL authors="dzy"

COPY target/http-client-0.0.1-SNAPSHOT.jar /app/http-client.jar

ENTRYPOINT ["java", "-Xms128m", "-Xmx128m", "-jar", "/app/http-client.jar"]