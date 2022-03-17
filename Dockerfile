FROM openjdk:19-alpine

COPY ./cities-0.0.1-SNAPSHOT.jar /cities-0.0.1-SNAPSHOT.jar


EXPOSE 8080
CMD ["java", "-jar", "cities-0.0.1-SNAPSHOT.jar"]

