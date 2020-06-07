package com.example.demo;

import com.example.demo.model.user.ClientUser;
import com.example.demo.repositories.StoreRepository;
import com.example.demo.repositories.threshold.MoneyThresholdRepository;
import com.example.demo.repositories.users.UserRepository;
import com.example.demo.services.users.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ServingWebContentApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServingWebContentApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(UserService userService){
		return args -> {
			userService.addUser("MargeSimpson@gmail.com", "123456");
			userService.addUser("HomeroSimpson@gmail.com", "cerveza");
			userService.addUser("BartSimpson@gmail.com", "aycaramba");
			userService.addUser("LisaSimpson@gmail.com", "yolosetodo");
			userService.addUser("MaggieSimpson@gmail.com", "nohabla");
		};
	}
}