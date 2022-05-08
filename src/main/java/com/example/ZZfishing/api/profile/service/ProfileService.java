package com.example.ZZfishing.api.profile.service;

import com.example.ZZfishing.api.profile.exception.ProfileEmailTakenException;
import com.example.ZZfishing.api.profile.exception.ProfileNotDeletedException;
import com.example.ZZfishing.api.profile.exception.ProfileNotFoundException;
import com.example.ZZfishing.api.profile.mapper.ProfileMapper;
import com.example.ZZfishing.api.profile.repository.ProfileRepository;
import com.example.ZZfishing.api.profile.repository.entity.Profile;
import com.example.ZZfishing.utils.IdUtil;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProfileService implements IProfileService {

    private final ProfileRepository profileRepository;
    private final ApplicationEventPublisher publisher;
    private final ProfileMapper profileMapper;

    public ProfileService(
            ProfileRepository profileRepository, ApplicationEventPublisher publisher, ProfileMapper profileMapper) {
                this.profileRepository = profileRepository;
                this.publisher = publisher;
                this.profileMapper = profileMapper;
    }

    @Override
    public Optional<Profile> get(long id) {
        IdUtil.assertId(id);
        return profileRepository.findById(id);
    }

    @Override
    public List<Profile> getProfiles() {
        return profileRepository.findAll();
    }

    @Override
    public Profile addNewProfile(Profile profile) {
        Optional<Profile> profileOptional = profileRepository
                .findProfileByEmail(profile.getEmail());
        if (profileOptional.isPresent()) {
            throw new ProfileEmailTakenException(profile.getEmail());
        }
        return profileRepository.save(profile);
    }

    @Override
    public void deleteProfile(Long profileId) {
        boolean exists = profileRepository.existsById(profileId);

        if (!exists) {
            throw new ProfileNotDeletedException(profileId);
        }
        profileRepository.deleteById(profileId);
    }
    @Override
    public Profile updateProfile(Long profileId, Profile profile) {
        Profile profileDB = profileRepository.findById(profileId).orElseThrow(
                () -> new ProfileNotFoundException(profileId));

        if (Objects.nonNull(profile.getFirstName()) &&
                !"".equalsIgnoreCase(profile.getFirstName())) {
                profileDB.setFirstName(profile.getFirstName());
        }
        if (Objects.nonNull(profile.getEmail()) &&
                !"".equalsIgnoreCase(profile.getEmail())) {
                profileDB.setEmail(profile.getEmail());
        }
        if (profile.getAge() != 0) {
            profileDB.setAge(profile.getAge());
        }
        return profileRepository.save(profileDB);
    }

    @Override
    public Profile getProfileById(Long profileId) {
        IdUtil.assertId(profileId);
        return profileRepository.findById(profileId).orElseThrow(
                () -> new ProfileNotFoundException(profileId));
    }

    @Override
    public Profile getProfileOrThrow(long profileId)
        throws ProfileNotFoundException {
            return profileRepository.findById(profileId)
                    .orElseThrow(() -> new ProfileNotFoundException(profileId));
        }
}
