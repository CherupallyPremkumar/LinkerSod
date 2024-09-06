# Stage 1: Build the application
FROM maven:3.8.4-openjdk-17 AS build

# Set the working directory
WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy the project and build the application
COPY . .
RUN mvn clean package

# Stage 2: Run the application
FROM openjdk:17-jdk

# Set the working directory
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/**.jar /app/**.jar

# Command to run the application
CMD ["java", "-jar", "yourapp.jar"]
