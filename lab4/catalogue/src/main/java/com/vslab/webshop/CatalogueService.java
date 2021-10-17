package com.vslab.webshop;

import com.vslab.webshop.category.Category;
import com.vslab.webshop.product.Product;
import com.vslab.webshop.utils.CustomErrorResponse;
import com.vslab.webshop.utils.URLUtils;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CatalogueService {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    public Product addProduct(Product product) {
        RestTemplate restTemplate = new RestTemplate();
        int categoryId = product.getCategoryId();
        URLUtils utils = new URLUtils();
        String productServiceURL = utils.getProductServiceURL() + "/product/";
        String categorieServiceURL = utils.getCategoryServiceURL() + "/category/";
        Category category = restTemplate.getForObject(categorieServiceURL +  Integer.toString(categoryId),Category.class);

        if (category != null){
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Product> requestEntity = new HttpEntity<>(product, headers);
            return restTemplate.postForObject(productServiceURL, requestEntity, Product.class);
        }else {
            throw new CustomErrorResponse("Ressource or Service not found");
        }
    }

    public void deleteCategory(int categoryId) {
        RestTemplate restTemplate = new RestTemplate();
        URLUtils utils = new URLUtils();
        String productServiceURL = utils.getProductServiceURL() + "/product/";
        String categorieServiceURL = utils.getCategoryServiceURL() + "/category/";

        Category cTest = restTemplate.getForObject(categorieServiceURL+ categoryId, Category.class);
        if (cTest != null) {
            Product[] products = restTemplate.getForObject(productServiceURL, Product[].class);
            for (Product product : products) {
                Product castedProduct = (Product)product;
                if (castedProduct.getCategoryId() == categoryId) {
                    restTemplate.delete(productServiceURL + castedProduct.getId());
                }
            }
            restTemplate.delete(categorieServiceURL + categoryId);
        }else {
            throw new CustomErrorResponse("Categorie not found");
        }
    }
}
