FROM openjdk:8u181-jdk-alpine3.8

WORKDIR /app
COPY target/UserService-0.0.1-Demo.jar .
EXPOSE 3006


CMD java -jar /app/UserService-0.0.1-Demo.jar --spring.datasource.url=jdbc:mysql://db.phornlert.me:3306/sealuser --spring.datasource.username=sealuser --spring.datasource.password=w0domLx9wwZptZeL --authenservice.jwt.secret=sealeiei

# FROM maven:3.6.0-jdk-8-alpine
