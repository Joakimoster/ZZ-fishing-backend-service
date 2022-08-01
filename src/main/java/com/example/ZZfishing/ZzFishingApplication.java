package com.example.ZZfishing;

import com.example.ZZfishing.api.catching.repository.CatchingRepository;
import com.example.ZZfishing.api.catching.repository.entity.Catching;
import com.example.ZZfishing.api.catching.repository.enums.FishMethod;
import com.example.ZZfishing.api.fish.repository.entity.Fish;
import com.example.ZZfishing.api.fish.repository.enums.FishSpecies;
import com.example.ZZfishing.api.fishinglure.repository.entity.FishingLure;
import com.example.ZZfishing.api.profile.repository.ProfileRepository;
import com.example.ZZfishing.api.profile.repository.entity.Profile;
import com.example.ZZfishing.api.user.repository.UserRepository;
import com.example.ZZfishing.api.user.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class ZzFishingApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ZzFishingApplication.class, args);
	}

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProfileRepository profileRepository;

	@Autowired
	private CatchingRepository catchingRepository;

	@Override
	public void run(String... args) throws Exception {

		/*
			User 1
		 */
		FishingLure fishingLure = new FishingLure();
		fishingLure.setType("Spin");
		fishingLure.setLabel("Bravo label");
		fishingLure.setLength(5);
		fishingLure.setWeight(10);

		Fish fish = new Fish();
		fish.setWeight(5);
		fish.setLength(10);
		fish.setFishSpecies(FishSpecies.ROACH);

		Catching catching = new Catching();
		catching.setCatchingDate(new Date());
		catching.setFishMethod(FishMethod.FLY_FISHING);
		catching.setLocation("Virsewum göl");
		catching.setReleased(true);

		Catching catching2 = new Catching();
		catching2.setCatchingDate(new Date());
		catching2.setFishMethod(FishMethod.BAIT_FISHING);
		catching2.setLocation("Wästerwik göl");
		catching2.setReleased(false);

		fishingLure.setCatching(catching);
		fishingLure.setCatching(catching2);
		fish.setCatching(catching);
		fish.setCatching(catching2);
		catching.setFishingLure(fishingLure);
		catching.setFish(fish);
		catching2.setFishingLure(fishingLure);
		catching2.setFish(fish);

		List<Catching> catchingList = new ArrayList<>();
		catchingList.add(catching);
		catchingList.add(catching2);

		User user = new User();
		user.setEmail("Apaserdig123@gmail.com");
		user.setPassword("Hejsan123");

		Profile profile = new Profile();
		profile.setFirstName("Kim");
		profile.setCountry("Sweden");
		profile.setEmail("Apaserdig123@gmail.com");
		profile.setAge(20);
		profile.setLastName("East");

		profile.setCatchings(catchingList);
		catching.setProfile(profile);
		catching2.setProfile(profile);

		user.setProfile(profile);
		profile.setUser(user);

		userRepository.save(user);
		catchingRepository.save(catching);


		/*
			User 2
		 */
		FishingLure fishingLure2 = new FishingLure();
		fishingLure2.setType("Old lure");
		fishingLure2.setLabel("Swedish label");
		fishingLure2.setLength(2);
		fishingLure2.setWeight(15);

		Fish fish2 = new Fish();
		fish2.setWeight(2);
		fish2.setLength(5);
		fish2.setFishSpecies(FishSpecies.SALMON);

		Catching catching3 = new Catching();
		catching3.setCatchingDate(new Date());
		catching3.setFishMethod(FishMethod.TROLLING);
		catching3.setLocation("Åseda göl");
		catching3.setReleased(false);

		Catching catching4 = new Catching();
		catching4.setCatchingDate(new Date());
		catching4.setFishMethod(FishMethod.SPINNING);
		catching4.setLocation("Wottne göl");
		catching4.setReleased(true);

		fishingLure2.setCatching(catching3);
		fishingLure2.setCatching(catching4);
		fish2.setCatching(catching3);
		fish2.setCatching(catching4);
		catching3.setFishingLure(fishingLure2);
		catching3.setFish(fish2);
		catching4.setFishingLure(fishingLure2);
		catching4.setFish(fish2);

		List<Catching> catchingList2 = new ArrayList<>();
		catchingList2.add(catching3);
		catchingList2.add(catching4);

		User user2 = new User();
		user2.setEmail("Kennadogshit@gmail.com");
		user2.setPassword("åhmans");

		Profile profile2 = new Profile();
		profile2.setFirstName("Pelle");
		profile2.setCountry("Sweden");
		profile2.setEmail("Kennadogshit@gmail.com");
		profile2.setAge(45);
		profile2.setLastName("Kjellberg");

		profile2.setCatchings(catchingList2);
		catching3.setProfile(profile2);
		catching4.setProfile(profile2);

		user2.setProfile(profile2);
		profile2.setUser(user2);

		userRepository.save(user2);
		catchingRepository.save(catching3);
	}
}
