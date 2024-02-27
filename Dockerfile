FROM openjdk:17
WORKDIR /
COPY target/treasure-game.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]