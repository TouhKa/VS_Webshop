package com.vslab.categories;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CategoriesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CategoriesApplication.class, args);
	}

}
