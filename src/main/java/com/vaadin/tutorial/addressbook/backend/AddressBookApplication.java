package com.vaadin.tutorial.addressbook.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class AddressBookApplication extends SpringBootServletInitializer {

    // Required for Spring Boot WAR deployment to external servers
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AddressBookApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(AddressBookApplication.class, args);
    }
}


