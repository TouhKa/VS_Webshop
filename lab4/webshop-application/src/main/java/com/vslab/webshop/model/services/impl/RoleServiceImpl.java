package com.vslab.webshop.model.services.impl;

import com.vslab.webshop.model.data.objects.Role;
import com.vslab.webshop.model.services.RoleService;
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
public class RoleServiceImpl implements RoleService {
    private final String authServerTokenURL = "http://auth-server:8092/oauth/token";
    private final String roleServiceUrl = "http://zuul:8091/roles/";
    private final String clientId = "webshop-client";
    private final String clientSecret = "webshop-secret";
    private final String roleServiceScope = "role_info";
    
    @Override
    public Role getRoleByLevel(int level) {
        OAuth2RestTemplate restTemplate = awesomeRestTemplate();
        return restTemplate.getForObject(roleServiceUrl + level, Role.class);
    }
    
    //pass client credentials to corresponding service
    private OAuth2ProtectedResourceDetails oAuth2ResourceDetails() {
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
    
    @Bean(name = "restTemplate") // has to be done at runtime because the authorization server would not be up otherwise
    private OAuth2RestTemplate awesomeRestTemplate() {
        AccessTokenRequest atr = new DefaultAccessTokenRequest();
        return new OAuth2RestTemplate(oAuth2ResourceDetails(), new DefaultOAuth2ClientContext(atr));
    }
}
