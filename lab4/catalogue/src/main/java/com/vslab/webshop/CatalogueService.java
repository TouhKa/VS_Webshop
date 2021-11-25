package com.vslab.webshop;

import com.vslab.webshop.category.Category;
import com.vslab.webshop.product.Product;
import com.vslab.webshop.utils.CustomErrorResponse;
import com.vslab.webshop.utils.URLUtils;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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

@Component
public class CatalogueService {
    
    private String authServerTokenURL = "http://auth-server:8092/oauth/token";
    private String clientId = "webshop-client";
    private String clientSecret = "webshop-secret";
    private String categoryServiceScope = "category_info";
    private String productServiceScope = "product_info";
    
    public OAuth2ProtectedResourceDetails oAuth2ProductResourceDetails() {
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
    
    public OAuth2ProtectedResourceDetails oAuth2CategoryResourceDetails() {
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
    
    @Bean
    @LoadBalanced
    public OAuth2RestTemplate makeProductRestTemplate() {
        AccessTokenRequest atr = new DefaultAccessTokenRequest();
        return new OAuth2RestTemplate(oAuth2ProductResourceDetails(), new DefaultOAuth2ClientContext(atr));
    }
    
    @Bean
    @LoadBalanced
    public OAuth2RestTemplate makeCategoryRestTemplate() {
        AccessTokenRequest atr = new DefaultAccessTokenRequest();
        return new OAuth2RestTemplate(oAuth2CategoryResourceDetails(), new DefaultOAuth2ClientContext(atr));
    }

    public Product addProduct(Product product) {
        RestTemplate productRestTemplate = makeProductRestTemplate();
        RestTemplate categoryRestTemplate = makeCategoryRestTemplate();
        int categoryId = product.getCategoryId();
        URLUtils utils = new URLUtils();
        String productServiceURL = utils.getProductServiceURL() + "/product/";
        String categoryServiceURL = utils.getCategoryServiceURL() + "/category/";
        Category category = categoryRestTemplate.getForObject(categoryServiceURL + categoryId,Category.class);

        if (category != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Product> requestEntity = new HttpEntity<>(product, headers);
            return productRestTemplate.postForObject(productServiceURL, requestEntity, Product.class);
        }else {
            throw new CustomErrorResponse("Resource or Service not found");
        }
    }

    public void deleteCategory(int categoryId) {
        RestTemplate productRestTemplate = makeProductRestTemplate();
        RestTemplate categoryRestTemplate = makeCategoryRestTemplate();
        URLUtils utils = new URLUtils();
        String productServiceURL = utils.getProductServiceURL() + "/product/";
        String categoryServiceURL = utils.getCategoryServiceURL() + "/category/";

        Category cTest = categoryRestTemplate.getForObject(categoryServiceURL+ categoryId, Category.class);
        if (cTest != null) {
            Product[] products = productRestTemplate.getForObject(productServiceURL, Product[].class);
            for (Product product : products) {
                Product castedProduct = product;
                if (castedProduct.getCategoryId() == categoryId) {
                    productRestTemplate.delete(productServiceURL + castedProduct.getId());
                }
            }
            categoryRestTemplate.delete(categoryServiceURL + categoryId);
        }else {
            throw new CustomErrorResponse("Categorie not found");
        }
    }
}
