package com.vslab.webshop.model.services.impl;

import com.vslab.webshop.model.data.objects.User;
import com.vslab.webshop.model.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableOAuth2Client
@SuppressWarnings("deprecation")
public class UserServiceImpl implements UserService {
    private String authServerTokenURL = "http://auth-server:8092/oauth/token";
    private String userServiceURL = "http://zuul:8091/users/";
    private String clientId = "webshop-client";
    private String clientSecret = "webshop-secret";
    private String userServiceScope = "user_info";

    // Create a Logger
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);


    //pass client credentials to corresponding service
    public OAuth2ProtectedResourceDetails oAuth2ResourceDetails() {
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

    public ResourceOwnerPasswordResourceDetails oAuthClientPasswordDetails() {
        ResourceOwnerPasswordResourceDetails details = new ResourceOwnerPasswordResourceDetails();

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



    @Bean(name = "restTemplate") // has to be done at runtime because the authorization server would not be up otherwise
    public OAuth2RestTemplate awesomeRestTemplate() {
        AccessTokenRequest atr = new DefaultAccessTokenRequest();
        return new OAuth2RestTemplate(oAuth2ResourceDetails(), new DefaultOAuth2ClientContext(atr));
    }

    @Bean(name = "passwordFestTemplate") // has to be done at runtime because the authorization server would not be up otherwise
    public OAuth2RestTemplate passwordRestTemplate() {
        AccessTokenRequest atr = new DefaultAccessTokenRequest();
        return new OAuth2RestTemplate(oAuthClientPasswordDetails(), new DefaultOAuth2ClientContext(atr));
    }

    public User[] getAllUsers() {
        OAuth2RestTemplate restTemplate = awesomeRestTemplate();
        return  restTemplate.getForObject(userServiceURL, User[].class);
    }

    public void addUser(User user) {
        OAuth2RestTemplate restTemplate = awesomeRestTemplate();
        restTemplate.postForObject(userServiceURL, user, User.class) ;
    }

    public User[] getLoginUser(String username, String password) {
        OAuth2RestTemplate restTemplate = passwordRestTemplate();
        ResourceOwnerPasswordResourceDetails passwordResourceDetails = (ResourceOwnerPasswordResourceDetails)
                restTemplate.getResource();
        passwordResourceDetails.setUsername(username);
        passwordResourceDetails.setPassword(password);
        return restTemplate.getForObject(userServiceURL, User[].class);
    }

}
