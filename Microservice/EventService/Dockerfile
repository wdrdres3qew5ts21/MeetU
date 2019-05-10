FROM openjdk:alpine
WORKDIR /app
ADD . .
ENV mongodb_host="54.255.208.210" 
ENV elastic_host="54.255.208.210"
ENV eureka_server="http://54.255.208.210:8761/eureka"
#ENV spring_cloud_config=
EXPOSE 3002:3002
CMD [ "java","-jar","target/event-0.0.1-SNAPSHOT.jar" ]