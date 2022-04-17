package com.example.ZZfishing.api.profile.controller;

import com.example.ZZfishing.api.profile.controller.dto.ProfileDto;
import com.example.ZZfishing.api.profile.service.ProfileService;
import com.example.ZZfishing.api.profile.repository.entity.Profile;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Fetch all profiles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All profiles are fetched"),
            @ApiResponse(responseCode = "500", description = "Unable to fetch all profiles due to internal error")
    })
    public ResponseEntity<List<Profile>> getProfiles() {
        List<Profile> profiles = profileService.getProfiles().stream().toList();
        HttpStatus httpStatus = HttpStatus.OK;
        return new ResponseEntity<>(profiles, httpStatus);
    }

    @GetMapping("/entity/{profileId}")
    @Operation(summary = "Get profileDto by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ProfileDto fetched by id"),
            @ApiResponse(responseCode = "500", description = "Unable to fetch profileDto due to internal error")
    })
    public ResponseEntity<ProfileDto> getProfileByDto(@PathVariable("profileId") Long profileId) {
        Profile profile = profileService.fetchProfileById(profileId);

        ProfileDto postResponse = modelMapper.map(profile, ProfileDto.class);
        return ResponseEntity.ok().body(postResponse);
    }

    @GetMapping("{profileId}")
    @Operation(summary = "Get profile by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profile fetched by id"),
            @ApiResponse(responseCode = "500", description = "Unable to fetch profile due to internal error")
    })
    public ResponseEntity<Profile> fetchProfileById(
            @PathVariable("profileId") Long profileId) {
                Profile profileDB = profileService.fetchProfileById(profileId);
                HttpStatus httpStatus = HttpStatus.OK;
                return new ResponseEntity<>(profileDB, httpStatus);
    }

    @PostMapping
    @Operation(summary = "Register a new profile")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "New profile was successfully created"),
            @ApiResponse(responseCode = "500", description = "Unable to create a new profile due to internal error")
    })
    public ResponseEntity<Profile> registerNewProfile(@RequestBody Profile profile) {
        Profile returnProfile = profileService.addNewProfile(profile);
        HttpStatus httpStatus = HttpStatus.CREATED;
        return new ResponseEntity<>(returnProfile, httpStatus);
    }

    @PostMapping("/entityDto")
    @Operation(summary = "Register a new profileDto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "New profileDto was successfully created"),
            @ApiResponse(responseCode = "500", description = "Unable to create a new profileDto due to internal error")
    })
    public ResponseEntity<ProfileDto> registerNewDtoProfile(@RequestBody ProfileDto profileDto) {
        Profile profileRequest = modelMapper.map(profileDto, Profile.class);
        Profile profile = profileService.addNewProfileDto(profileRequest);

        ProfileDto profileResponse = modelMapper.map(profile, ProfileDto.class);
        return new ResponseEntity<>(profileResponse, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{profileId}")
    @Operation(summary = "Delete profile by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profile was successfully deleted"),
            @ApiResponse(responseCode = "500", description = "Unable to delete the profile due to internal error")
    })
    public ResponseEntity<Profile> deleteProfile(@PathVariable ("profileId") Long profileId) {
        profileService.deleteProfile(profileId);
        HttpStatus httpStatus = HttpStatus.OK;
        return new ResponseEntity<>(httpStatus);
    }

    @PutMapping(path = "{profileId}")
    @Operation(summary = "Update profile by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profile was successfully updated"),
            @ApiResponse(responseCode = "500", description = "Unable to update the profile due to internal error")
    })
    public ResponseEntity<Profile> updateProfile(
            @PathVariable("profileId") Long profileId,
            @RequestBody Profile profile) {
                Profile returnProfile = profileService.updateProfile(profileId, profile);
                HttpStatus httpStatus = HttpStatus.OK;
                return new ResponseEntity<>(returnProfile, httpStatus);
    }
}
