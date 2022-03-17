FROM openjdk:19-alpine

COPY ./target/webservice-cities-0.0.1.jar /cities-0.0.1-SNAPSHOT.jar


EXPOSE 8080
CMD ["java", "-jar", "cities-0.0.1-SNAPSHOT.jar"]

