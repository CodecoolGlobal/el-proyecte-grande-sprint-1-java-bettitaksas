# Use an official OpenJDK runtime as a parent image
FROM openjdk:11-jre-slim

# Set the working directory in the container
WORKDIR ./

# Copy the JAR file into the container at /app
COPY target/your-spring-boot-app.jar ./your-spring-boot-app.jar

# Expose the port your Spring Boot app will run on
EXPOSE 8080

# Define the command to run your Spring Boot application
CMD ["java", "-jar", "your-spring-boot-app.jar"]
