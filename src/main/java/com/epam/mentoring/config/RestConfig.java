package com.epam.mentoring.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ServiceConfig.class, SecurityConfig.class})
@ComponentScan("com.epam.mentoring.controller")
public class RestConfig extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(RestConfig.class, args);
    }

//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(RestConfig.class);
//    }
}
