#escape=`
FROM openjdk:alpine
WORKDIR /app
ADD meetu-firebase-key.json .
ADD target/event-0.0.1-SNAPSHOT.jar .
ENV spring.profiles.active=production
ENV eureka_server="http://localhost:8761/eureka"
#ENV spring_cloud_config=
EXPOSE 3002:3002
CMD [ "java","-jar","event-0.0.1-SNAPSHOT.jar" ]