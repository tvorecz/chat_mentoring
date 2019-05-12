package com.epam.mentoring.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Import({ServiceConfig.class, SecurityConfig.class})
@ComponentScan("com.epam.mentoring")
@EntityScan("com.epam.mentoring.entity")
@EnableJpaRepositories(basePackages = "com.epam.mentoring.dal.repository")
public class RestConfig extends SpringBootServletInitializer {


    public static void main(String[] args) {
        SpringApplication.run(new Class[]{RestConfig.class}, args);
    }
}
