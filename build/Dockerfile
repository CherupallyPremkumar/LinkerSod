# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk
# rest of your Dockerfile



# Set the working directory in the container
WORKDIR /app

# Copy the application's JAR file to the container
COPY build-package/target/*.jar /app/app.jar

# Make port 8080 available to the world outside this contain

EXPOSE 8001


# Run the jar file
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
