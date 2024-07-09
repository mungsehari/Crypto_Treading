FROM openjdk:21-slim
COPY target/treading-0.0.1-SNAPSHOT.jar treading-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/treading-0.0.1-SNAPSHOT.jar "]
EXPOSE 5454



