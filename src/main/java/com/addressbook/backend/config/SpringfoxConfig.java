package com.addressbook.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;
import java.util.Optional;

@Configuration
@EnableSwagger2
public class SpringfoxConfig {

    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.addressbook.backend"))
                .paths(PathSelectors.any())
                .build()
                .groupName("addressbook");
    }

    // Workaround to fix compatibility issues with Spring Boot 2.7.x and Springfox
    @Autowired
    public void configureHandlerMappings(Optional<List<RequestMappingHandlerMapping>> handlerMappings) {
        handlerMappings.ifPresent(mappings -> mappings.removeIf(mapping -> mapping.getPatternParser() != null));
    }
}
