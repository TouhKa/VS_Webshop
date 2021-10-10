package com.vslab.webshop;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class RoleApplication {
	public static void main(String[] args) {

		SpringApplication.run(RoleApplication.class, args);
	}
	@Bean
	public CommandLineRunner defaultUser(RoleRepo repository) {
		return (args) -> {
			// create admin and user roles
			repository.save(new Role("admin"));
			repository.save(new Role("user"));
		};
	}
}
