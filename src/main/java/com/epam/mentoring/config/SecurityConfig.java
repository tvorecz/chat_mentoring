package com.epam.mentoring.config;

import com.epam.mentoring.security.AuthenticationExceptionHandler;
import com.epam.mentoring.security.JwtTokenHeaderBuilder;
import com.epam.mentoring.security.filter.JwtAuthenticationFilter;
import com.epam.mentoring.security.filter.JwtAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    @Qualifier("userServiceImpl")
    private UserDetailsService userDetailsService;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new MessageDigestPasswordEncoder("MD5");
    }

    @Bean
    public JwtTokenHeaderBuilder jwtTokenHeaderBuilder() {
        return new JwtTokenHeaderBuilder();
    }

    @Bean
    public AuthenticationExceptionHandler authenticationExceptionHandler() {
        return new AuthenticationExceptionHandler();
    }

    @Bean
    public UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.setAllowedMethods(Arrays.asList(new String[]{"POST", "GET", "PUT", "DELETE", "OPTIONS"}));


        source.registerCorsConfiguration("/**", config);

        return source;
    }

//    @Bean
//    public ServletWebServerFactory servletContainer() {
//        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
//            @Override
//            protected void postProcessContext(Context context) {
//                SecurityConstraint securityConstraint = new SecurityConstraint();
//                securityConstraint.setUserConstraint("CONFIDENTIAL");
//                SecurityCollection collection = new SecurityCollection();
//                collection.addPattern("/*");
//                securityConstraint.addCollection(collection);
//                context.addConstraint(securityConstraint);
//            }
//        };
//        tomcat.addAdditionalTomcatConnectors(redirectConnector());
//        return tomcat;
//    }
//
//    private Connector redirectConnector() {
//
//        Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
//        connector.setScheme("http");
//        connector.setPort(8080);
//        connector.setSecure(false);
//        connector.setRedirectPort(8443);
//        return connector;
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .configurationSource(urlBasedCorsConfigurationSource())
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/*")
                .permitAll()
                .antMatchers("/chat", "/chat/**", "/chat/**/message", "/chat/**/message/**")
                .hasRole("USER")
                .antMatchers("/", "/**", "/user", "/user/**")
                .permitAll()
                .and()
                .addFilter(new JwtAuthenticationFilter(authenticationManager(), jwtTokenHeaderBuilder()))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), jwtTokenHeaderBuilder()))
                .csrf()
                .disable()
//                .requiresChannel()
//                .antMatchers("/")
//                .requiresSecure()
//                .anyRequest()
//                .requiresSecure()
//                .and()
//                .portMapper()
//                .http(8080)
//                .mapsTo(8443)
//                .http(80)
//                .mapsTo(443)
//                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationExceptionHandler());
//                .and()
//                .requiresChannel()
//                .anyRequest().requiresSecure();
//                .antMatchers("/", "/**")
//                .requiresSecure();
    }
}
