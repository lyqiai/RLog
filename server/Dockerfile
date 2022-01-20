FROM openjdk:8

RUN apt-get update && apt-get install -y maven
COPY . /logserver
RUN  cd /project && mvn package

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-Dblabla", "-jar","/logserver/target/log-0.0.1-SNAPSHOT.jar"]