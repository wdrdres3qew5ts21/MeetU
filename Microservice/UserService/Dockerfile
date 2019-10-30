FROM openjdk:11-slim
WORKDIR /app
ENV spring.profiles.active=production
ENV spring_cloud_config=https://www.meetu-milktea.tk:8585
ADD target/userservice-0.0.1-Demo.jar .
ADD meetu-firebase-key.json .
EXPOSE 3001:3001
CMD [ "sh", "-c", "java -Dspring.profiles.active=production -Dfile.encoding=UTF-8 -XX:+UseContainerSupport  -jar /app/userservice-0.0.1-Demo.jar " ]
#CMD [ "sh", "-c", "java -Dspring.profiles.active=production -Xmx300m -Xss512k -XX:CICompilerCount=2 -Dfile.encoding=UTF-8 -XX:+UseContainerSupport -Djava.security.egd=file:/dev/./urandom -jar /app/userservice-0.0.1-Demo.jar " ]




