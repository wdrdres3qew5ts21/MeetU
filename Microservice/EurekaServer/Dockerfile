FROM openjdk:alpine
WORKDIR /app
ADD . .
EXPOSE 8761:8761
CMD [ "java","-jar","target/eurekaserver-0.0.1-SNAPSHOT.jar" ]