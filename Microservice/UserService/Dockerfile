FROM openjdk:8u181-jdk-alpine3.8

WORKDIR /app
ENV spring.profiles.active=production
ADD target/userservice-0.0.1-Demo.jar .
ADD meetu-firebase-key.json .
EXPOSE 3001:3001

CMD ["java" "-jar" "userservice-0.0.1-Demo.jar"]

# FROM maven:3.6.0-jdk-8-alpine
