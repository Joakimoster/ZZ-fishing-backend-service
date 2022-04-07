package com.example.ZZfishing.api.profile.service;

import com.example.ZZfishing.api.profile.exception.ProfileEmailTakenException;
import com.example.ZZfishing.api.profile.exception.ProfileNotDeletedException;
import com.example.ZZfishing.api.profile.exception.ProfileNotFoundException;
import com.example.ZZfishing.api.profile.repository.ProfileRepository;
import com.example.ZZfishing.api.profile.repository.entity.Profile;
import com.example.ZZfishing.utils.IdUtil;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileService(
            ProfileRepository profileRepository,
            ApplicationEventPublisher publisher) {
                this.profileRepository = profileRepository;
    }

    public Optional<Profile> get(long id) {
        IdUtil.assertId(id);
        return profileRepository.findById(id);
    }

    public List<Profile> getProfiles() {
        return profileRepository.findAll();
    }

    public void addNewProfile(Profile profile) {
        Optional<Profile> profileOptional = profileRepository
                .findProfileByEmail(profile.getEmail());
        if (profileOptional.isPresent()) {
            throw new ProfileEmailTakenException(profile.getEmail());
        }
        profileRepository.save(profile);
    }

    public Profile addNewProfileDto(Profile profile) {
        Optional<Profile> profileOptional = profileRepository
                .findProfileByEmail(profile.getEmail());
        if (profileOptional.isPresent()) {
            throw new ProfileEmailTakenException(profile.getEmail());
        }
        return profileRepository.save(profile);
    }

    public void deleteProfile(Long profileId) {
        boolean exists = profileRepository.existsById(profileId);

        if (!exists) {
            throw new ProfileNotDeletedException(profileId);
        }
        profileRepository.deleteById(profileId);
    }
    public void updateProfile(Long profileId, Profile profile) {
        Profile profileDB = profileRepository.findById(profileId).orElseThrow(
                () -> new ProfileNotFoundException(profileId));

        if (Objects.nonNull(profile.getName()) &&
                !"".equalsIgnoreCase(profile.getName())) {
                profileDB.setName(profile.getName());
        }
        if (Objects.nonNull(profile.getEmail()) &&
                !"".equalsIgnoreCase(profile.getEmail())) {
                profileDB.setEmail(profile.getEmail());
        }
        if (profile.getAge() != 0) {
            profileDB.setAge(profile.getAge());
        }
        profileRepository.save(profileDB);
    }

    public Profile fetchProfileById(Long profileId) {
        IdUtil.assertId(profileId);
        return profileRepository.findById(profileId).orElseThrow(
                () -> new ProfileNotFoundException(profileId));
    }

    protected Profile getProfileOrThrow(long profileId)
        throws ProfileNotFoundException {
            return profileRepository.findById(profileId)
                    .orElseThrow(() -> new ProfileNotFoundException(profileId));
        }
}
