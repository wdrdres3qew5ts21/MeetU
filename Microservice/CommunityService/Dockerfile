FROM openjdk:11-slim
WORKDIR /app
ENV spring.profiles.active=production
ENV spring_cloud_config=https://www.meetu-milktea.tk:8585
ADD target/communityservice-0.0.1-SNAPSHOT.jar .
ADD meetu-firebase-key.json .
#ENV spring_cloud_config=
EXPOSE 3003:3003
CMD [ "sh", "-c", "java -Dspring.profiles.active=production -Dfile.encoding=UTF-8 -XX:+UseContainerSupport  -jar /app/communityservice-0.0.1-SNAPSHOT.jar " ]
#CMD [ "java","-jar","communityservice-0.0.1-SNAPSHOT.jar" ]