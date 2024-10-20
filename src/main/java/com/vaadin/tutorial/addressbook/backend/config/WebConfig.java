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

