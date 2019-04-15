FROM maven:alpine
WORKDIR /app
ADD . .
RUN mvn clean package
CMD [ "java","-jar","target/event-0.0.1-SNAPSHOT.jar" ]