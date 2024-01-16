FROM maven:3.9.0-eclipse-temurin-17-alpine AS build

COPY . .

RUN mvn clean package -DskipTests

FROM openjdk:21-slim
COPY --from=build /target/tasking-0.0.1-SNAPSHOT.jar tasking-0.0.1-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "tasking-0.0.1-SNAPSHOT.jar"]