# Use the official Spring Boot image or a JDK image
FROM openjdk:17-jdk-alpine

# Add the JAR file to the container
COPY ./target/addressbook-1.0.jar /app/addressbook-1.0.jar

# Expose the port the app runs on
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "/app/addressbook-1.0.jar"]


