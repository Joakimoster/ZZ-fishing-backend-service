package com.example.ZZfishing.api.profile.controller;

import com.example.ZZfishing.api.profile.controller.dto.ProfileDto;
import com.example.ZZfishing.api.profile.repository.entity.Profile;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IProfileController {
    ResponseEntity<List<Profile>> getProfiles();

    ResponseEntity<ProfileDto> getProfileDto(Long id);

    ResponseEntity<Profile> getProfileById(Long Id);

    ResponseEntity<Profile> registerNewProfile(Profile profile);

    ResponseEntity<ProfileDto> registerNewProfileDto(ProfileDto profileDto);

    ResponseEntity<Profile> deleteProfile(Long id);

    ResponseEntity<Profile> updateProfile(Long id, Profile profile);
}
