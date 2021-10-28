package hska.iwi.eShopMaster.model.services.impl;

import hska.iwi.eShopMaster.model.services.CatalogueService;
import hska.iwi.eShopMaster.model.services.ProductService;
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

public class CatalogueServiceImpl implements CatalogueService {
    private final String authServerTokenURL = "http://auth-server:8092/oauth/token";
    private final String productServiceURL = "http://zuul:8091/product/";
    private final String categoryServiceURL = "http://zuul:8091/category/";
    private final String clientId = "webshop-client";
    private final String clientSecret = "webshop-secret";
    private final String productServiceScope = "product_info";
    private final String categoryServiceScope = "category_info";
    
    //pass client credentials to corresponding service
    private OAuth2ProtectedResourceDetails oAuth2ProductResourceDetails() {
        ClientCredentialsResourceDetails details = new ClientCredentialsResourceDetails();
        details.setClientId(clientId);
        details.setClientSecret(clientSecret);
        details.setAccessTokenUri(authServerTokenURL);
        
        List<String> scope = new ArrayList<>();
        scope.add(productServiceScope);
        details.setScope(scope);
        
        details.setAuthenticationScheme(AuthenticationScheme.header);
        details.setClientAuthenticationScheme(AuthenticationScheme.header);
        details.setId("1");
        details.setTokenName("Product_Service");
        return details;
    }
    
    //pass client credentials to corresponding service
    public OAuth2ProtectedResourceDetails oAuthCategory2ResourceDetails() {
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
        details.setTokenName("Category_Service");
        return details;
    }
    
    @Bean(name = "restTemplate") // has to be done at runtime because the authorization server would not be up otherwise
    public OAuth2RestTemplate productRestTemplate() {
        AccessTokenRequest atr = new DefaultAccessTokenRequest();
        return new OAuth2RestTemplate(oAuth2ProductResourceDetails(), new DefaultOAuth2ClientContext(atr));
    }
    
    @Bean(name = "restTemplate") // has to be done at runtime because the authorization server would not be up otherwise
    public OAuth2RestTemplate categoryRestTemplate() {
        AccessTokenRequest atr = new DefaultAccessTokenRequest();
        return new OAuth2RestTemplate(oAuth2ProductResourceDetails(), new DefaultOAuth2ClientContext(atr));
    }
    
    @Override
    public void deleteCategory(int id) {
        // TODO first we check if we actually need it :D
    }
}
