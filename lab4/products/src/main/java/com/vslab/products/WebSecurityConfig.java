package com.vslab.products;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    protected void configure(final HttpSecurity http) throws Exception {
        //authentication is on all endpoints required
        http.antMatcher("/**")
                .authorizeRequests()

                // TEST: define an exception for a path without authentification
                //TODO: Remove after testing
                .antMatchers("/product/{productId}").permitAll()
                .anyRequest().authenticated();
    }
}
