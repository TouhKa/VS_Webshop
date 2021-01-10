package com.vslab.catalogue.utils;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;

public class URLUtils {
    private final String PRODUCT = "product-service";
    private final String CATEGORY = "category-service";
    private String productServiceURL;
    private String categoryServiceURL;

    public URLUtils(){
        try {
            LoadBalancerClient loadBalancer = BeanUtil.getBean(LoadBalancerClient.class);
            ServiceInstance productServiceInstance = loadBalancer.choose(PRODUCT);
            ServiceInstance categoryServiceInstance = loadBalancer.choose(CATEGORY);
            productServiceURL = productServiceInstance.getUri().toString();
            categoryServiceURL = categoryServiceInstance.getUri().toString();
        }catch (NullPointerException e){
            productServiceURL = "http://" + PRODUCT + ":8085";
            categoryServiceURL = "http://" + PRODUCT + ":8086";
        }
    }

    public String getProductServiceURL(){
        return this.productServiceURL;
    }

    public String getCategoryServiceURL(){
        return this.categoryServiceURL;
    }
}
