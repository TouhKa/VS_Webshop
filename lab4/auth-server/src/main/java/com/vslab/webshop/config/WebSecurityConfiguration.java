package com.vslab.webshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// see: https://www.javadevjournal.com/spring/spring-security-userdetailsservice/

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    //triggered by checkTokenAccess() and checkKeyAccess()
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
                auth.parentAuthenticationManager(authenticationManagerBean())
                .inMemoryAuthentication()
                .withUser("Daniel")
                .password(passwordEncoder().encode("password"))
                .roles("USER");
    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
