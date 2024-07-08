FROM openjdk:21-jdk-slim
ARG JAR_FILE=target/*.jar
COPY ./target/treading-0.0.1-SNAPSHOT.jar treading.jar
ENTRYPOINT ["java","-jar","/treading.jar"]
