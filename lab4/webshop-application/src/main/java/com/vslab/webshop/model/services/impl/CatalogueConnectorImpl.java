package com.vslab.webshop.model.services.impl;

import com.vslab.webshop.model.data.objects.Product;
import com.vslab.webshop.model.services.CatalogueConnector;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;

import java.util.ArrayList;
import java.util.List;

/**
 * the Catalogue connector uses the Resource Owner Password Credentials to authenticate at the catalogue microservice
 */
public class CatalogueConnectorImpl implements CatalogueConnector {
    
    private String authServerTokenURL = "http://auth-server:8092/oauth/token";
    private String categoryServiceURL = "http://zuul:8091/category/";
    private String clientId = "webshop-client";
    private String clientSecret = "webshop-secret";
    private String categoryServiceScope = "category_info";
    
    //pass client credentials to corresponding service
    public OAuth2ProtectedResourceDetails oAuth2ResourceDetails() {
        ClientCredentialsResourceDetails details = new ClientCredentialsResourceDetails();
        details.setClientId(clientId);
        details.setClientSecret(clientSecret);
        details.setAccessTokenUri(authServerTokenURL);
        
        List<String> scope = new ArrayList<>();
        scope.add(categoryServiceScope);
        details.setScope(scope);
        
        details.setAuthenticationScheme(AuthenticationScheme.header);
        details.setClientAuthenticationScheme(AuthenticationScheme.header);
        details.setId("1");
        details.setTokenName("Catalogue_Service");
        return details;
    }
    
    @Bean(name = "passwordRestTemplate") // has to be done at runtime because the authorization server would not be up otherwise
    public OAuth2RestTemplate makeRestTemplate() {
        AccessTokenRequest atr = new DefaultAccessTokenRequest();
        return new OAuth2RestTemplate(oAuthClientPasswordDetails(), new DefaultOAuth2ClientContext(atr));
    }
    
    
    @Override
    public Product addProduct(Product product) {

    }
    
    @Override
    public void deleteCategory(int id) {

    }
}
