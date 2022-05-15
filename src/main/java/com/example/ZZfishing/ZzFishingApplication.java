package com.example.ZZfishing;

import com.example.ZZfishing.api.profile.repository.ProfileRepository;
import com.example.ZZfishing.api.profile.repository.entity.Profile;
import com.example.ZZfishing.api.user.repository.UserRepository;
import com.example.ZZfishing.api.user.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class ZzFishingApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ZzFishingApplication.class, args);
	}

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProfileRepository profileRepository;

	@Override
	public void run(String... args) throws Exception {
		User user = new User();
		user.setEmail("Apaserdig123@gmail.com");
		user.setPassword("Hejsan123");

		Profile profile = new Profile();
		profile.setFirstName("Kim");
		profile.setCountry("Sweden");
		profile.setEmail("Apaserdig123@gmail.com");
		profile.setAge(20);
		profile.setLastName("East");
		profile.setCatchings(new ArrayList<>());

		user.setProfile(profile);
		profile.setUser(user);

		userRepository.save(user);
	}
}
