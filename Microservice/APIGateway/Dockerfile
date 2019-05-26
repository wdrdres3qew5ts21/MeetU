FROM openjdk:alpine
WORKDIR /app
ADD . .
ENV mongodb_host="54.255.208.210" 
ENV elastic_host="54.255.208.210"
ENV eureka_server="https://www.meetu-milktea.tk:8761/eureka"
#ENV spring_cloud_config=
EXPOSE 4000:4000
CMD [ "java","-jar","target/gateway-0.0.1-SNAPSHOT.jar" ]