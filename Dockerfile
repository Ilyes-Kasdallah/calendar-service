# Use an official Java runtime as a parent image
FROM openjdk:17-jre-slim

# Set the working directory in the container
WORKDIR /app

# Copy the jar file into the container
COPY target/calendar-service.jar /app/calendar-service.jar

# Run the jar file
ENTRYPOINT ["java", "-jar", "/app/calendar-service.jar"]

# Expose the port the application runs on
EXPOSE 8080
