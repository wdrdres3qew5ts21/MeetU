FROM openjdk:alpine
WORKDIR /app
ADD meetu-firebase-key.json .
ADD target/gateway-0.0.1-SNAPSHOT.jar .
ENV spring.profiles.active=production
ENV eureka_server="https://www.meetu-milktea.tk:8761/eureka"
#ENV spring_cloud_config=
EXPOSE 4000:4000
CMD [ "java","-jar","gateway-0.0.1-SNAPSHOT.jar" ]