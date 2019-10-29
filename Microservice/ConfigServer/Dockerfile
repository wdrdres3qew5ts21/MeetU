#escape=`
FROM openjdk:11-slim
WORKDIR /app
ENV spring.profiles.active=production
ADD target/configserver-0.0.1-SNAPSHOT.jar .
#ENV elasticsearch.host=https://meetu-milktea.tk
#ENV spring_cloud_config=
EXPOSE 8585:8585
CMD [ "java","-jar","configserver-0.0.1-SNAPSHOT.jar" ]