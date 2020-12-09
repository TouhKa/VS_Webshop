package com.vslab.account;

import com.vslab.account.utils.CustomErrorResponse;
import com.vslab.account.utils.URLUtils;
import com.vslab.roles.Role;
import com.vslab.users.User;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class AccountService {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    public void removeRole(int roleId) {
        RestTemplate restTemplate = new RestTemplate();
        URLUtils utils = new URLUtils();
        String userServiceUrl = utils.getUserServiceUrl() + "/users/";
        String roleServiceUrl = utils.getRoleServiceUrl() + "/role/";

        Role roleToRemove = restTemplate.getForObject(roleServiceUrl + roleId, Role.class);
        if (roleToRemove != null) {
            User[] users = restTemplate.getForObject(userServiceUrl, User[].class);
            for (User user : users) {
                if (user.getRoleId() == roleToRemove.getId()) {
                    User newUser = new User(user.getRoleId() - 1, user.getFirstname(), user.getLastname(), user.getUsername(), user.getPassword());
                    restTemplate.put(userServiceUrl, newUser);
                }
            }
        } else {
            throw new CustomErrorResponse("Role not found");
        }
    }
}
