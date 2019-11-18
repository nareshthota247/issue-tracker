FROM openjdk:8
ADD target/pinguin.jar app.jar
EXPOSE 8082
ENTRYPOINT java -jar app.jar