# Use the official OpenJDK image with JDK 17 as a base image
FROM eclipse-temurin:17-jdk

# Set environment variables for optimal Spring Boot performance in Docker
ENV JAVA_OPTS="-Xms512M -Xmx1024M -Djava.security.egd=file:/dev/./urandom"

# Create a directory for the app
WORKDIR /app

# Copy the JAR file from the target directory (assuming built by Maven)
COPY ./target/addressbook-1.0.jar /app/addressbook-1.0.jar

# Expose the application port (Spring Boot default is 8080)
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "/app/addressbook-1.0.jar"]

