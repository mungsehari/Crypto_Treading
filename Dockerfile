FROM openjdk:21-slim
COPY target/*.jar treading.jar
ENTRYPOINT ["java","-jar","/treading.jar"]




