package com.vslab.webshop;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
public class UserApplication {
	public static void main(String[] args) {

		SpringApplication.run(UserApplication.class, args);
	}

	@Bean
	public CommandLineRunner defaultUser(UserRepo repository) {
		return (args) -> {
			// create admin user
			repository.save(new User(2, "admin", "admin", "admin", "admin"));
		};
	}
}
