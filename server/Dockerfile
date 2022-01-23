FROM openjdk:8

RUN apt-get update && apt-get install -y maven
COPY ./target /logserver

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-Dblabla", "-jar","/logserver/log-0.0.1-SNAPSHOT.jar"]