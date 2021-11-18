package com.vslab.webshop.config;

import com.vslab.webshop.DockerLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    DockerLogger logger = new DockerLogger(CustomUserDetailsService.class.getSimpleName());

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private UserServiceAction userServiceAction = new UserServiceAction();

    //see : https://www.javadevjournal.com/spring/spring-security-userdetailsservice/
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails;
        String roleName = "ADMIN";
        logger.write("User to be found: " + username);

        try {

            com.vslab.webshop.config.User user = userServiceAction.getUserByName(username);
            if (user == null) {
                throw new Exception("User not exisiting");
            }
            logger.write("User found: " + user.getUsername());
            logger.close();
            if (user.getRoleId() == 1) {
                roleName = "USER";
            }
            //return org.springframework.security.core.userdetails.User
            userDetails = User.withUsername(user.getUsername())
                                            .password(passwordEncoder().encode(user.getPassword()))
                                            .roles(roleName)
                                            .build();
        }catch (Exception e) {
            userDetails = User.withUsername("error_user")
                    .password(passwordEncoder().encode("error_password"))
                    .roles(roleName)
                    .build();
        }

        return userDetails;
    }

}
