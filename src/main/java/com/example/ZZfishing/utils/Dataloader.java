package com.example.ZZfishing.utils;

import com.example.ZZfishing.api.catching.repository.CatchingRepository;
import com.example.ZZfishing.api.catching.repository.entity.Catching;
import com.example.ZZfishing.api.fish.repository.FishRepository;
import com.example.ZZfishing.api.fish.repository.entity.Fish;
import com.example.ZZfishing.api.fishinglure.repository.FishingLureRepository;
import com.example.ZZfishing.api.fishinglure.repository.entity.FishingLure;
import com.example.ZZfishing.api.profile.repository.ProfileRepository;
import com.example.ZZfishing.api.profile.repository.entity.Profile;
import com.example.ZZfishing.api.user.repository.UserRepository;
import com.example.ZZfishing.api.user.repository.entity.User;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class Dataloader implements ApplicationRunner {

    UserRepository userRepository;
    FishRepository fishRepository;
    ProfileRepository profileRepository;
    CatchingRepository catchingRepository;
    FishingLureRepository fishingLureRepository;

    public Dataloader(UserRepository userRepository, FishRepository fishRepository,
                      ProfileRepository profileRepository, CatchingRepository catchingRepository,
                      FishingLureRepository fishingLureRepository)
    {
        this.userRepository = userRepository;
        this.fishRepository = fishRepository;
        this.profileRepository = profileRepository;
        this.catchingRepository = catchingRepository;
        this.fishingLureRepository = fishingLureRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        /*Profile profile = new Profile("Hanses", "Husse", "Apaserdig123@gmail.com", 25, "Sweden");
        profileRepository.save(profile);

        User user = new User("Hejsan213", "Apaserdig123@gmail.com", profile);
        userRepository.save(user);


        catchingRepository.save(new Catching("Tuna"));
        fishingLureRepository.save(new FishingLure("Flug", 5, 5, "Best label"));
        fishRepository.save(new Fish(9, 1, "Small fishes"));*/
    }
}
