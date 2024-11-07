package com.addressbook.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Address Book API",
        version = "1.0",
        description = "API documentation for the Address Book application"
    )
)
public class SwaggerConfig {
    // Configuration is now handled by springdoc-openapi annotations.
}


