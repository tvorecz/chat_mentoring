package com.epam.mentoring.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Import({SecurityConfig.class, SwaggerConfig.class})
@ComponentScan("com.epam.mentoring")
@EntityScan("com.epam.mentoring.entity")
@EnableJpaRepositories(basePackages = "com.epam.mentoring.dal.repository")
public class RestConfig extends SpringBootServletInitializer {

    public static void main(String[] args) {
//        SpringApplication springApplication = new SpringApplication(RestConfig.class);
//        springApplication.setAdditionalProfiles("ssl");
//        springApplication.run(args);

        SpringApplication.run(new Class[]{RestConfig.class}, args);
    }

//    @Bean
//    public FilterRegistrationBean<CorsFilter> corsFilterFilterRegistrationBean() {
//        FilterRegistrationBean<CorsFilter> corsFilterFilterRegistrationBean = new FilterRegistrationBean<>();
//
//        corsFilterFilterRegistrationBean.setFilter(new CorsFilter());
//        corsFilterFilterRegistrationBean.addUrlPatterns("/**");
//
//        return corsFilterFilterRegistrationBean;
//    }


//    @Configuration
//    @EnableWebMvc
//    public static class WebConfig implements WebMvcConfigurer {
//
//        @Override
//        public void addCorsMappings(CorsRegistry registry) {
//            registry.addMapping("/**");
//        }
//    }
}
