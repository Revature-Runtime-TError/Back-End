# this is the base image
FROM openjdk:latest

# copy the jar file from the local to the container which will created be in the future
COPY target/social-media-0.0.1-SNAPSHOT.jar proj3.jar

# the entrypoint command to be run once the container is started
ENTRYPOINT ["java", "-jar", "/proj3.jar"]