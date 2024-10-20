#!/bin/bash

# Create the main directory structure
mkdir -p AddressBookApp/src/main/java/com/vaadin/tutorial/addressbook/backend/{config,controller,model,service}
mkdir -p AddressBookApp/src/main/java/com/vaadin/tutorial/addressbook/frontend/view
mkdir -p AddressBookApp/src/main/resources/{static/css,templates}
mkdir -p AddressBookApp/src/main/resources/META-INF
mkdir -p AddressBookApp/src/main/webapp/WEB-INF
mkdir -p AddressBookApp/src/test/java

# Create Java files with content

# WebConfig.java
cat > AddressBookApp/src/main/java/com/vaadin/tutorial/addressbook/backend/config/WebConfig.java <<EOL
package com.vaadin.tutorial.addressbook.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Map static resources like CSS and JS to the appropriate directories
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }

    // Additional Spring MVC configuration (if needed) can be added here
}
EOL

# ContactController.java
cat > AddressBookApp/src/main/java/com/vaadin/tutorial/addressbook/backend/controller/ContactController.java <<EOL
package com.vaadin.tutorial.addressbook.backend.controller;

import com.vaadin.tutorial.addressbook.backend.model.Contact;
import com.vaadin.tutorial.addressbook.backend.service.ContactService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public List<Contact> getAllContacts() {
        return contactService.findAll();
    }

    @PostMapping
    public void addContact(@RequestBody Contact contact) {
        contactService.addContact(contact);
    }
}
EOL

# Contact.java
cat > AddressBookApp/src/main/java/com/vaadin/tutorial/addressbook/backend/model/Contact.java <<EOL
package com.vaadin.tutorial.addressbook.backend.model;

public class Contact {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    // Constructors, getters, setters
    public Contact() {}

    public Contact(Long id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
EOL

# ContactService.java
cat > AddressBookApp/src/main/java/com/vaadin/tutorial/addressbook/backend/service/ContactService.java <<EOL
package com.vaadin.tutorial.addressbook.backend.service;

import com.vaadin.tutorial.addressbook.backend.model.Contact;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactService {
    private final List<Contact> contacts = new ArrayList<>();

    public List<Contact> findAll() {
        return contacts;
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }
}
EOL

# AddressBookApplication.java
cat > AddressBookApp/src/main/java/com/vaadin/tutorial/addressbook/backend/AddressBookApplication.java <<EOL
package com.vaadin.tutorial.addressbook.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class AddressBookApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(AddressBookApplication.class, args);
    }
}
EOL

# AddressBookView.java
cat > AddressBookApp/src/main/java/com/vaadin/tutorial/addressbook/frontend/view/AddressBookView.java <<EOL
package com.vaadin.tutorial.addressbook.frontend.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("")
public class AddressBookView extends VerticalLayout {

    public AddressBookView() {
        Button addContactBtn = new Button("Add Contact");
        addContactBtn.addClickListener(event -> {
            // Add logic to open the ContactForm
        });
        add(addContactBtn);
    }
} 
EOL

# ContactForm.java
cat > AddressBookApp/src/main/java/com/vaadin/tutorial/addressbook/frontend/view/ContactForm.java <<EOL
package com.vaadin.tutorial.addressbook.frontend.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;

public class ContactForm extends FormLayout {
    private TextField firstName = new TextField("First name");
    private TextField lastName = new TextField("Last name");
    private TextField email = new TextField("Email");

    private Button saveButton = new Button("Save");

    public ContactForm() {
        add(firstName, lastName, email, saveButton);
    }
}
EOL

# Create static CSS file
cat > AddressBookApp/src/main/resources/static/css/styles.css <<EOL
/* General body styles */
body {
    font-family: Arial, sans-serif;
    background-color: #f0f0f0;
    margin: 0;
    padding: 0;
    color: #333;
}

/* Container for the main layout */
.container {
    width: 100%;
    max-width: 1200px;
    margin: 0 auto;
    padding: 20px;
}

/* Style for headers */
h1,
h2,
h3 {
    color: #2c3e50;
    text-align: center;
    margin-bottom: 20px;
}

/* Button styles */
button {
    background-color: #4CAF50;
    color: white;
    padding: 10px 20px;
    border: none;
    cursor: pointer;
    border-radius: 5px;
    font-size: 16px;
}

button:hover {
    background-color: #45a049;
}
EOL

# Create application.properties file
cat > AddressBookApp/src/main/resources/application.properties <<EOL
server.port=8080
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.h2.console.enabled=true
vaadin.productionMode=true
EOL

# Create index.html
cat > AddressBookApp/src/main/resources/templates/index.html <<EOL
<!DOCTYPE html>
<html>
<head>
    <title>Address Book</title>
    <link rel="stylesheet" type="text/css" href="/static/css/styles.css">
</head>
<body>
    <div class="container">
        <h1>Address Book</h1>
        <p>Manage your contacts efficiently</p>
    </div>
</body>
</html>
EOL

echo "Project structure and files created successfully."
