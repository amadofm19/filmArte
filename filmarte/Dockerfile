FROM openjdk:17-alpine
LABEL maintainer="filmarte"
COPY target/filmarte-0.0.1-SNAPSHOT.jar /filmarte-app.jar
ENTRYPOINT ["java", "-jar", "/filmarte-app.jar"]