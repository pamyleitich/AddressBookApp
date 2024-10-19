# Use the official Tomcat image
FROM tomcat:9.0.96-jdk11

# Remove the default Tomcat ROOT app
RUN rm -rf /usr/local/tomcat/webapps/ROOT

# Copy the pre-built WAR file from Jenkins into the webapps directory
COPY ./target/addressbook-1.0.war /usr/local/tomcat/webapps/ROOT.war

# Expose the default Tomcat port
EXPOSE 8080

# Start the Tomcat server
CMD ["catalina.sh", "run"]
