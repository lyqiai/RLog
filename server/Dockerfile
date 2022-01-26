FROM openjdk:8-alpine

FROM maven:3.6.0-jdk-8-alpine

COPY . /logserver

RUN cd /logserver && mvn package