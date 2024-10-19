# AddressBook Application

This project is a web-based Address Book application that allows users to create, read, and manage contacts. The project is built with Spring Boot for the backend and Vaadin for the frontend. The application can be packaged as a `.war` file and deployed on Tomcat.

## Features
- Create, read, and manage contacts (first name, last name, email)
- REST API for contact management
- Simple UI built with Vaadin

## Project Structure

/src ├── main │ ├── java │ │ ├── com.example.addressbook.backend │ │ │ ├── model │ │ │ ├── service │ │ │ ├── controller │ │ │ └── config │ │ └── com.example.addressbook.frontend │ │ └── view │ ├── resources │ ├── static │ ├── templates │ └── application.properties └── pom.xml 


## Prerequisites

Ensure the following are installed:

- **JDK 17**
- **Maven**
- **Docker** (if using Dockerized deployment)
- **Tomcat** (if deploying as a WAR)

## Build and Run

### Running Locally

1. Clone the repository:
   ```bash
   git clone https://github.com/your-repo/addressbook-app.git
   cd addressbook-app
