FROM openjdk:alpine
WORKDIR /app
ENV spring.profiles.active=production
ADD target/communityservice-0.0.1-SNAPSHOT.jar .
ADD meetu-firebase-key.json .
#ENV spring_cloud_config=
EXPOSE 3003:3003
CMD [ "java","-jar","communityservice-0.0.1-SNAPSHOT.jar" ]