# Stage 1: Build the application
FROM maven:3.8.4-openjdk-17-slim as build

# Set the working directory inside the container
WORKDIR /pet

# Copy the pom.xml and download dependencies first (to take advantage of Docker cache)
COPY pom.xml . 
RUN mvn dependency:go-offline

# Copy the rest of the application code
COPY src ./src 

# Build the application (skip tests for faster build)
RUN mvn clean package -DskipTests

# Stage 2: Runtime image (smaller image for production)
FROM openjdk:17-jdk-slim  

# Set the working directory in the runtime container
WORKDIR /pet  

# Copy the jar file from the build stage into the runtime stage
COPY --from=build pet/target/Springboot-pet.jar app.jar 

# Expose port 8080 for the Spring Boot application
EXPOSE 8080 

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
