
FROM openjdk:21-jdk

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} treading.jar

ENTRYPOINT ["java","-jar","/treading.jar"]

EXPOSE 8761


