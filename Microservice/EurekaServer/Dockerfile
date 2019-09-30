FROM openjdk:alpine
WORKDIR /app
ADD target/eurekaserver-0.0.1-SNAPSHOT.jar .
EXPOSE 8761:8761
CMD [ "java","-jar","eurekaserver-0.0.1-SNAPSHOT.jar" ]