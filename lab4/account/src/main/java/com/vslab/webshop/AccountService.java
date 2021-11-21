package com.vslab.webshop;

import com.vslab.webshop.utils.CustomErrorResponse;
import com.vslab.webshop.utils.URLUtils;
import com.vslab.webshop.roles.Role;
import com.vslab.webshop.users.User;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


/**
 * the account service uses client credentials authentication to interact with the users and roles services
 */
@Component
public class AccountService {
    
    private String authServerTokenURL = "http://auth-server:8092/oauth/token";
    private String clientId = "webshop-client";
    private String clientSecret = "webshop-secret";
    private String userServiceScope = "user_info";
    private final String roleServiceScope = "role_info";
    
    public OAuth2ProtectedResourceDetails oAuth2UserResourceDetails() {
        ClientCredentialsResourceDetails details = new ClientCredentialsResourceDetails();
        details.setClientId(clientId);
        details.setClientSecret(clientSecret);
        details.setAccessTokenUri(authServerTokenURL);
        List<String> scope = new ArrayList<>();
        scope.add(userServiceScope);
        details.setScope(scope);
        details.setAuthenticationScheme(AuthenticationScheme.header);
        details.setClientAuthenticationScheme(AuthenticationScheme.header);
        details.setId("1");
        details.setTokenName("User_Service");
        return details;
    }
    
    public OAuth2ProtectedResourceDetails oAuth2RoleResourceDetails() {
        ClientCredentialsResourceDetails details = new ClientCredentialsResourceDetails();
        details.setClientId(clientId);
        details.setClientSecret(clientSecret);
        details.setAccessTokenUri(authServerTokenURL);
        List<String> scope = new ArrayList<>();
        scope.add(roleServiceScope);
        details.setScope(scope);
        details.setAuthenticationScheme(AuthenticationScheme.header);
        details.setClientAuthenticationScheme(AuthenticationScheme.header);
        details.setId("1");
        details.setTokenName("Role_Service");
        return details;
    }
    
    @Bean
    @LoadBalanced
    public OAuth2RestTemplate makeUserRestTemplate() {
        AccessTokenRequest atr = new DefaultAccessTokenRequest();
        return new OAuth2RestTemplate(oAuth2UserResourceDetails(), new DefaultOAuth2ClientContext(atr));
    }
    
    @Bean
    @LoadBalanced
    public OAuth2RestTemplate makeRoleRestTemplate() {
        AccessTokenRequest atr = new DefaultAccessTokenRequest();
        return new OAuth2RestTemplate(oAuth2RoleResourceDetails(), new DefaultOAuth2ClientContext(atr));
    }

    public void removeRole(long roleId) {
        RestTemplate roleRestTemplate = makeRoleRestTemplate();
        RestTemplate userRestTemplate = makeUserRestTemplate();
        URLUtils utils = new URLUtils();
        String userServiceUrl = utils.getUserServiceUrl() + "/users/";
        String roleServiceUrl = utils.getRoleServiceUrl() + "/roles/";

        Role roleToRemove = roleRestTemplate.getForObject(roleServiceUrl + roleId, Role.class);
        if (roleToRemove != null) {
            User[] users = userRestTemplate.getForObject(userServiceUrl, User[].class);
            Role[] allRoles = roleRestTemplate.getForObject(roleServiceUrl, Role[].class);
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
                    userRestTemplate.put(userServiceUrl, newUser);
                }
            }
            roleRestTemplate.delete(roleServiceUrl + roleId);
        } else {
            throw new CustomErrorResponse("Role not found");
        }
    }
}
