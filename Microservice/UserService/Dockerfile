FROM openjdk:8u181-jdk-alpine3.8

WORKDIR /app
COPY target/userservice-0.0.1-Demo.jar .
ENV mongodb_host="54.255.208.210" 
ENV elastic_host="54.255.208.210"
ENV eureka_server="http://54.255.208.210:8761/eureka"
#ENV spring_cloud_config=
EXPOSE 3001:3001


CMD java -jar /app/userservice-0.0.1-Demo.jar

# FROM maven:3.6.0-jdk-8-alpine
