package com.vslab.webshop.model.services.impl;

import com.vslab.webshop.model.data.objects.Product;
import com.vslab.webshop.model.services.ProductService;
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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Configuration
@EnableOAuth2Client
@SuppressWarnings("deprecation")
public class ProductServiceImpl implements ProductService {
    private String authServerTokenURL = "http://auth-server:8092/oauth/token";
    private String productServiceURL = "http://zuul:8091/product/";
    private String clientId = "webshop-client";
    private String clientSecret = "webshop-secret";
    private String productServiceScope = "product_info";


    //pass client credentials to corresponding service
    public OAuth2ProtectedResourceDetails oAuth2ResourceDetails() {
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

    @Bean(name = "restTemplate") // has to be done at runtime because the authorization server would not be up otherwise
    public OAuth2RestTemplate awesomeRestTemplate() {
        AccessTokenRequest atr = new DefaultAccessTokenRequest();
        return new OAuth2RestTemplate(oAuth2ResourceDetails(), new DefaultOAuth2ClientContext(atr));
    }

    public Product getProduct(Integer productId) {
        OAuth2RestTemplate restTemplate = awesomeRestTemplate();
        return restTemplate.getForObject(productServiceURL + productId, Product.class);
    }

    public void addProduct(Product product) {
        OAuth2RestTemplate restTemplate = awesomeRestTemplate();
        restTemplate.put(productServiceURL, product, Product.class);
    }

    // maybe we can scrap the optionals here?
    public List<Product> getAllProducts(Optional<String> searchValue, Optional<String> priceMinValue, Optional<String> priceMaxValue) {
        String parameters = this.createParameterString(searchValue, priceMinValue, priceMaxValue);
        OAuth2RestTemplate restTemplate = awesomeRestTemplate();
        Product[] products = restTemplate.getForObject(productServiceURL+parameters, Product[].class);
        return Arrays.asList(products);
    }

    private String createParameterString(Optional<String> searchValue, Optional<String> priceMinValue, Optional<String> priceMaxValue) {
        String url = "?";
        if ( (searchValue == null) && (priceMaxValue == null) && (priceMinValue == null)) {
            return "";
        }else if (searchValue != null && priceMinValue != null && priceMaxValue != null) {
            return  url + "searchValue=" + searchValue.get() + "&" + "priceMinValue=" + priceMinValue.get() + "&" + "priceMaxValue=" + priceMaxValue.get();

        }else if (searchValue != null && priceMinValue != null && priceMaxValue == null) {
            return  url + "searchValue=" + searchValue.get() + "&" + "priceMinValue=" + priceMinValue.get();

        }else if (searchValue != null && priceMaxValue != null && priceMinValue == null) {
            return  url + "searchValue=" + searchValue.get() + "&" + "priceMaxValue=" + priceMaxValue.get();

        }else if (priceMinValue != null && priceMaxValue != null && searchValue== null) {
            return  url +"priceMaxValue=" + priceMaxValue.get() + "&" + "priceMinValue=" + priceMinValue.get();

        }else if (searchValue != null ) {
            return url + "searchValue=" + searchValue.get();

        }else if (priceMinValue != null) {
            return url + "priceMinValue=" + priceMinValue.get();

        }else if (priceMaxValue != null) {
            return url + "priceMaxValue=" + priceMaxValue.get();
        }
        return "";
    }

    public List<Product> getAllProducts() {
        OAuth2RestTemplate restTemplate = awesomeRestTemplate();
        Product[] products = restTemplate.getForObject(productServiceURL, Product[].class);
        return Arrays.asList(products != null ? products : new Product[0]);
    }

    public void updateProduct(Product product) {
        OAuth2RestTemplate restTemplate = awesomeRestTemplate();
        restTemplate.put(productServiceURL, product, Product.class);
    }

    public void deleteProduct(int productId) {
        OAuth2RestTemplate restTemplate = awesomeRestTemplate();
        restTemplate.delete(productServiceURL + productId, Product.class);
    }

    public Product getProductByName(String name) {
        for (Product product : getAllProducts()) {
            if (product.getName().equals(name)) {
                return product;

            }
        }
        return new Product("fallback", 0.0, 1, "");
    }
}
