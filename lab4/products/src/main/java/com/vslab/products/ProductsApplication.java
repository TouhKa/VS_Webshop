package com.vslab.products;

import com.google.j2objc.annotations.ReflectionSupport;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableDiscoveryClient
@SuppressWarnings("deprecation")
public class ProductsApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProductsApplication.class, args);
	}
}
