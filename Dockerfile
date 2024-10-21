# Use the official Tomcat image with JDK 17
FROM tomcat:9.0.96-jdk17

# Remove the default Tomcat webapps (optional to keep the container clean)
RUN rm -rf /usr/local/tomcat/webapps/ROOT /usr/local/tomcat/webapps/ROOT.war

# Copy the WAR file from the target directory to Tomcat's webapps directory
COPY ./target/addressbook-1.0.war /usr/local/tomcat/webapps/ROOT.war

# Expose the default Tomcat port
EXPOSE 8080

# Start Tomcat server
CMD ["catalina.sh", "run"]



