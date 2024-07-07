FROM maven:3.9.5-openjdk-21 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:21-jdk-slim
COPY --from=build /target/treading-0.0.1-SNAPSHOT.jar treading.jar
EXPOSE 5454
ENTRYPOINT["java","-jar","treading.jar"]
