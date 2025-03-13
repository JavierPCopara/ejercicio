FROM openjdk:17-jdk-slim as build
WORKDIR /app
COPY target/*.jar ejercicio.jar
EXPOSE 8089
ENTRYPOINT ["java", "-jar", "/app/ejercicio.jar"]
