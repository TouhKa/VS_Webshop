package com.vslab.webshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@SuppressWarnings("deprecation")
public class ProductsApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProductsApplication.class, args);
	}
}
