package com.example.ZZfishing.api.profile.service;

import com.example.ZZfishing.api.profile.exception.ProfileNotFoundException;
import com.example.ZZfishing.api.profile.repository.entity.Profile;

import java.util.List;
import java.util.Optional;

public interface IProfileService {
    Optional<Profile> get(long id);

    List<Profile> getProfiles();

    Profile addNewProfile(Profile profile);

    void deleteProfile(Long profileId);

    Profile updateProfile(Long profileId, Profile profile);

    Profile getProfileById(Long profileId);

    Profile getProfileOrThrow(long profileId)
            throws ProfileNotFoundException;
}
