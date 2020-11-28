package com.vslab.products;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class ProductsApplication {
	private static final Logger log = LoggerFactory.getLogger(ProductsApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(ProductsApplication.class, args);
	}

	//Testing with some dummy data
//	@Bean
//	public CommandLineRunner demo(ProductRepo repository) {
//		return (args) -> {
//			// save a few Products
//			repository.save(new Product("Espresso", 50.0, 1, "so strong that the dead are awakened"));
//			repository.save(new Product("Blacktea", 99.99, 2, "best tea leaves"));
//			repository.save(new Product("Christmas Wine", 0.5, 3, "sind klein und rund..."));
//
//			// fetch all Products
//			log.info("Products found with findAll():");
//			log.info("-------------------------------");
//			for (Product prod : repository.findAll()) {
//				log.info(prod.toString());
//			}
//			log.info("");
//
//			// fetch an individual Product by ID
//			Product product = repository.findById(1);
//			log.info("Product found with findById(1):");
//			log.info("--------------------------------");
//			log.info(product.toString());
//			log.info("");
//		};
//	}
}
