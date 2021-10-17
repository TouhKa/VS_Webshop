package com.vslab.webshop.utils;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;

public class URLUtils {
    private String userServiceUrl;
    private String roleServiceUrl;

    public URLUtils(){
        String USER_SERVICE = "user-service";
        String ROLE_SERVICE = "role-service";
        try {
            LoadBalancerClient loadBalancer = BeanUtil.getBean(LoadBalancerClient.class);
            ServiceInstance productServiceInstance = loadBalancer.choose(USER_SERVICE);
            ServiceInstance categoryServiceInstance = loadBalancer.choose(ROLE_SERVICE);
            userServiceUrl = productServiceInstance.getUri().toString();
            roleServiceUrl = categoryServiceInstance.getUri().toString();
        }catch (NullPointerException e){
            userServiceUrl = "http://" + USER_SERVICE + ":8088";
            roleServiceUrl = "http://" + ROLE_SERVICE + ":8089";
        }
    }

    public String getUserServiceUrl(){
        return this.userServiceUrl;
    }

    public String getRoleServiceUrl(){
        return this.roleServiceUrl;
    }
}
