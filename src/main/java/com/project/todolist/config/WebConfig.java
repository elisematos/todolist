package com.project.todolist.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        corsRegistry.addMapping("/**")
                .allowedOrigins("https://to-do-list-library-angular.herokuapp.com")
                .allowedMethods("GET, POST, DELETE, PATCH, PUT, HEAD, OPTIONS")
                .maxAge(3600L)
                .allowedHeaders("Origin, X-Requested-With, Content-Type, Accept")
                .exposedHeaders("*")
                .allowCredentials(true);
    }
}
