package com.payaut.grocery_store.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Allow all paths
                .allowedOrigins("http://localhost:8080", "https://grocery-store-production-d971.up.railway.app") // Add the Swagger UI URL
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Allow necessary HTTP methods
                .allowedHeaders("*"); // Allow all headers
    }
}

