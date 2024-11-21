# Use an official Java runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the jar file into the container
COPY target/calendar-service-0.0.1-SNAPSHOT.jar /app/calendar-service.jar

# Expose the port the application runs on
EXPOSE 8080

# Set the default command to run the jar file
ENTRYPOINT ["java", "-jar", "/app/calendar-service.jar"]
