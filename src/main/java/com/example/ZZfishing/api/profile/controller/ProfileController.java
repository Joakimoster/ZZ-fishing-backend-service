package com.example.ZZfishing.api.profile.controller;

import com.example.ZZfishing.api.profile.controller.dto.ProfileDto;
import com.example.ZZfishing.api.profile.service.ProfileService;
import com.example.ZZfishing.api.profile.repository.entity.Profile;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/profile")
public class ProfileController {

    private final ProfileService profileService;

    @Autowired
    private ModelMapper modelMapper;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping
    public List<Profile> getProfiles() {
        return profileService.getProfiles();
    }

    @GetMapping("/entity/{profileId}")
    public ResponseEntity<ProfileDto> getProfileByDto(@PathVariable("profileId") Long profileId) {
        Profile profile = profileService.fetchProfileById(profileId);

        ProfileDto postResponse = modelMapper.map(profile, ProfileDto.class);
        return ResponseEntity.ok().body(postResponse);
    }

    @GetMapping("{profileId}")
    public Profile fetchProfileById(@PathVariable("profileId") Long profileId) {
        return profileService.fetchProfileById(profileId);
    }

    @PostMapping
    public void registerNewProfile(@RequestBody Profile profile) {
        profileService.addNewProfile(profile);
    }

    @PostMapping("/entityDto")
    public ResponseEntity<ProfileDto> registerNewDtoProfile(@RequestBody ProfileDto profileDto) {
        Profile profileRequest = modelMapper.map(profileDto, Profile.class);
        Profile profile = profileService.addNewProfileDto(profileRequest);

        ProfileDto profileResponse = modelMapper.map(profile, ProfileDto.class);
        return new ResponseEntity<>(profileResponse, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{profileId}")
    public void deleteProfile(@PathVariable ("profileId") Long profileId) {
        profileService.deleteProfile(profileId);
    }

    @PutMapping(path = "{profileId}")
    public void updateProfile(
            @PathVariable("profileId") Long profileId,
            @RequestBody Profile profile) {
                profileService.updateProfile(profileId, profile);
    }
}
