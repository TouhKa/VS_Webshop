package hska.iwi.eShopMaster.controller.microservices;

import hska.iwi.eShopMaster.model.businessLogic.manager.impl.microservices.Category;
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
public class CategoryServiceAction {
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
        details.setTokenName("Category_Service");
        return details;
    }

    @Bean(name = "restTemplate") // has to be done at runtime because the authorization server would not be up otherwise
    public OAuth2RestTemplate awesomeRestTemplate() {
        AccessTokenRequest atr = new DefaultAccessTokenRequest();
        return new OAuth2RestTemplate(oAuth2ResourceDetails(), new DefaultOAuth2ClientContext(atr));
    }

    public Category[] getAll() {
        OAuth2RestTemplate restTemplate = awesomeRestTemplate();
        return restTemplate.getForObject(categoryServiceURL, Category[].class) ;
    }
    public Category addCategory(Category category) {
        OAuth2RestTemplate restTemplate = awesomeRestTemplate();
        return restTemplate.postForObject(categoryServiceURL, category, Category.class);
    }
    public Category getCategoryById(int id) {
        OAuth2RestTemplate restTemplate = awesomeRestTemplate();
        return restTemplate.getForObject(categoryServiceURL + id, Category.class);
    }

    public Category updateCategorie(Category category) {
        OAuth2RestTemplate restTemplate = awesomeRestTemplate();
        restTemplate.put(categoryServiceURL, category, Category.class);
        return restTemplate.getForObject(categoryServiceURL + category.getId(), Category.class);
    }
    public void deleteCategory(int id) {
        OAuth2RestTemplate restTemplate = awesomeRestTemplate();
        restTemplate.delete(categoryServiceURL + id);
    }

    public Category getCategoryByName(String name) {
        Category[] categories = this.getAll();
        for (int i = 0; i < categories.length; i++) {
            if (categories[i].getName().equals(name)) {
                return categories[i];

            }
        }
        return new Category("fallback");
    }

}