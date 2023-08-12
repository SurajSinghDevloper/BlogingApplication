package com.blog.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class OriginConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Map all endpoints
                .allowedOrigins("*") // Allow requests from any origin (you can restrict it to specific origins)
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Allow specified methods
                .allowedHeaders("*"); // Allow all headers
    }
}
