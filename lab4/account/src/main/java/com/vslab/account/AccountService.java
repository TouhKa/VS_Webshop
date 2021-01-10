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

    public void removeRole(long roleId) {
        RestTemplate restTemplate = new RestTemplate();
        URLUtils utils = new URLUtils();
        String userServiceUrl = utils.getUserServiceUrl() + "/users/";
        String roleServiceUrl = utils.getRoleServiceUrl() + "/roles/";

        Role roleToRemove = restTemplate.getForObject(roleServiceUrl + roleId, Role.class);
        if (roleToRemove != null) {
            User[] users = restTemplate.getForObject(userServiceUrl, User[].class);
            Role[] allRoles = restTemplate.getForObject(roleServiceUrl, Role[].class);
            // find a new role to choose, because the current role will be removed
            long highestRoleId = -1;
            for (Role role : allRoles) {
                if (role.getId() > highestRoleId && role.getId() != roleId) {
                    highestRoleId = role.getId();
                }
            }
            for (User user : users) {
                // every user that has this role gets his role updated to the next highest role
                if (user.getRoleId() == roleToRemove.getId()) {
                    User newUser = new User(user.getId(), highestRoleId, user.getFirstname(), user.getLastname(), user.getUsername(), user.getPassword());
                    restTemplate.put(userServiceUrl, newUser);
                }
            }
            restTemplate.delete(roleServiceUrl + roleId);
        } else {
            throw new CustomErrorResponse("Role not found");
        }
    }
}
