# Use an official OpenJDK runtime as a parent image
FROM eclipse-temurin:17.0.8_7-jre

# Set the working directory in the container
WORKDIR ./

# Copy the JAR file into the container at /app
COPY target/demo-0.0.1-SNAPSHOT.jar ./demo-0.0.1-SNAPSHOT.jar

# Expose the port your Spring Boot app will run on
EXPOSE 8080

# Define the command to run your Spring Boot application
CMD ["java", "-jar", "demo-0.0.1-SNAPSHOT.jar"]
