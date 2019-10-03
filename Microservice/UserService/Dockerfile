FROM openjdk:alpine
WORKDIR /app
ENV spring.profiles.active=production
ADD target/userservice-0.0.1-Demo.jar .
ADD meetu-firebase-key.json .
EXPOSE 3001:3001

CMD [ "java", "-jar","userservice-0.0.1-Demo.jar" ]



