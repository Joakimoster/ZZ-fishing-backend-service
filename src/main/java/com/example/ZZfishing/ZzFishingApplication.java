package com.example.ZZfishing;

import com.example.ZZfishing.api.user.repository.entity.User;
import com.example.ZZfishing.api.user.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ZzFishingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZzFishingApplication.class, args);
	}

	/*@Bean
	CommandLineRunner run(UserService userService) {
		return args -> {
			userService.addNewUser(new User("hejsan213", "Apaserdig123@gmail.com"));
		};
	}*/
}
