package com.vslab.catalogue;


import com.vslab.catalogue.utils.CustomErrorResponse;
import com.vslab.catalogue.utils.URLUtils;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.Optional;


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

    public Product[] searchCatalogue(Optional<String> categoryName, Optional<String> productDetails, Optional<Integer> priceMinValue, Optional<Integer> priceMaxValue) {
        // get category id
        // get all products where cateory id
        //filter min, max price, details
        return null;
    }

    public void deleteCategory(int categoryId) {
        //TODO get all products where cateogory
        // delete returned products
        //delete category
        //return # deleted objects
    }
}
