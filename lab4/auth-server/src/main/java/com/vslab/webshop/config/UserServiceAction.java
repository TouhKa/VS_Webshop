package com.vslab.webshop.config;

import com.vslab.webshop.DockerLogger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableOAuth2Client
@SuppressWarnings("deprecation")
public class UserServiceAction {
    private String authServerTokenURL = "http://auth-server:8092/oauth/token";
    private String userServiceURL = "http://zuul:8091/users/";
    private String clientId = "webshop-client";
    private String clientSecret = "webshop-secret";
    private String userServiceScope = "user_info";

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
        details.setId("1"); //is this needed?
        details.setTokenName("User_Service");
        return details;
    }

    @Bean(name = "restTemplate") // has to be done at runtime because the authorization server would not be up otherwise
    public OAuth2RestTemplate awesomeRestTemplate() {
        AccessTokenRequest atr = new DefaultAccessTokenRequest();
        return new OAuth2RestTemplate(oAuth2ResourceDetails(), new DefaultOAuth2ClientContext(atr));
    }

    public User getUserByName(String username) {
        OAuth2RestTemplate restTemplate = awesomeRestTemplate();

        User[] users = restTemplate.getForObject(userServiceURL, User[].class);
        try {
            for (User user : users) {
                if (user.getUsername().equals(username)) {
                    return user;

                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
