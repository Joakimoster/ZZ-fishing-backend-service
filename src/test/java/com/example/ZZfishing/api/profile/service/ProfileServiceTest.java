package com.example.ZZfishing.api.profile.service;

import com.example.ZZfishing.api.profile.exception.ProfileNotFoundException;
import com.example.ZZfishing.api.profile.mapper.ProfileMapper;
import com.example.ZZfishing.api.profile.repository.ProfileRepository;
import com.example.ZZfishing.api.profile.repository.entity.Profile;
import com.example.ZZfishing.model.exception.IdNotValidException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ProfileServiceTest {

    private final ProfileRepository repository = Mockito.mock(ProfileRepository.class);
    private final ApplicationEventPublisher publisher = Mockito.mock(ApplicationEventPublisher.class);
    private final ProfileMapper profileMapper = Mockito.mock(ProfileMapper.class);
    private final ProfileService profileService = new ProfileService(repository, publisher, profileMapper);

    private static final Long VALID_ID = 1L;
    private static final Long INVALID_ID = -1L;

    @Test
    void canGetProfiles() {
        long id = VALID_ID;
        Profile profile = getProfile(id);
        List<Profile> profileList = new ArrayList<>();
        profileList.add(profile);

        when(repository.findAll()).thenReturn(profileList);

        List<Profile> fetchedProfiles = profileService.getProfiles();
        assertThat(fetchedProfiles.size()).isGreaterThan(0);
    }

    @Test
    void canVerifyProfiles() {
        setupRepositoryList();
        List<Profile> profiles = profileService.getProfiles();
        Assertions.assertFalse(profiles.isEmpty());
    }

    @Test
    void canVerifyProfileById() {
        IdNotValidException thrown =
                Assertions.assertThrows(
                        IdNotValidException.class,
                        () -> {
                            profileService.getProfileById(INVALID_ID);
                        });
        Assertions.assertEquals(HttpStatus.NOT_ACCEPTABLE, thrown.getStatus());
        Assertions.assertEquals(
                String.format("ID: %s is not valid", INVALID_ID), thrown.getMessage());
    }

    @Test
    void canVerifyProfileIdOrThrowInvalidProfileId() {
        ProfileNotFoundException thrown =
                Assertions.assertThrows(
                        ProfileNotFoundException.class,
                        () -> {
                            profileService.getProfileOrThrow(INVALID_ID);
                        });
        Assertions.assertEquals(HttpStatus.NOT_FOUND, thrown.getStatus());
        Assertions.assertEquals(
                String.format("Unable to find profile by id: %s", INVALID_ID),
                thrown.getMessage());
    }

    @Test
    void canAddNewProfile() {
        Profile profile = getProfile(VALID_ID);
        //when
        repository.save(profile);

        //then
        ArgumentCaptor<Profile> profileArgumentCaptor =
                ArgumentCaptor.forClass(Profile.class);
        verify(repository).save(profileArgumentCaptor.capture());

        Profile capturedProfile = profileArgumentCaptor.getValue();
        assertThat(capturedProfile).isEqualTo(profile);
    }

    @Test
    void canDeleteProfile() {
        //given
        long id = VALID_ID;
        given(repository.existsById(id))
                .willReturn(true);
        //when
        profileService.deleteProfile(id);

        //then
        verify(repository).deleteById(id);
    }

    @Test
    void canUpdateProfile() {
        //given
        Profile profile = getProfile(VALID_ID);
        profile.setName("Abu");

        given(repository.findById(VALID_ID))
                .willReturn(Optional.of(profile));
        //when
        profileService.updateProfile(VALID_ID, profile);

        //then
        verify(repository).save(profile);
    }

    private void setupRepositoryList() {
        when(repository.findAll()).thenReturn(getProfileList());
    }

    private List<Profile> getProfileList() {
        List<Profile> profileList = new ArrayList<>();
        profileList.add(getProfile(1));
        profileList.add(getProfile(2));
        return profileList;
    }

    private Profile getProfile(long id) {
        Profile profile = new Profile();
        profile.setId(id);
        profile.setAge(20);
        profile.setEmail("Johansson@gmail.com");
        profile.setName("Alfred");
        return profile;
    }
}