#escape=`
FROM openjdk:11-slim
WORKDIR /app
ENV spring_cloud_config=https://www.meetu-milktea.tk:8585
ADD meetu-firebase-key.json .
ADD target/event-0.0.1-SNAPSHOT.jar .
ENV spring.profiles.active=production
#ENV spring_cloud_config=
EXPOSE 3002:3002
CMD [ "sh", "-c", "java -Dspring.profiles.active=production -Dfile.encoding=UTF-8 -XX:+UseContainerSupport  -jar /app/event-0.0.1-SNAPSHOT.jar " ]
# CMD [ "sh", "-c", "java -Dspring.profiles.active=production -Xmx300m -Xss512k -XX:CICompilerCount=2 -Dfile.encoding=UTF-8 -XX:+UseContainerSupport -Djava.security.egd=file:/dev/./urandom -jar /app/event-0.0.1-SNAPSHOT.jar " ]
# CMD [ "java -XX:+UseContainerSupport -Xmx128m -jar event-0.0.1-SNAPSHOT.jar"  ] 