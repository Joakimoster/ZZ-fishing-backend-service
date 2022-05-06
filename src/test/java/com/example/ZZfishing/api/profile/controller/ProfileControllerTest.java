package com.example.ZZfishing.api.profile.controller;

import com.example.ZZfishing.api.profile.mapper.ProfileMapper;
import com.example.ZZfishing.api.profile.repository.ProfileRepository;
import com.example.ZZfishing.api.profile.repository.entity.Profile;
import com.example.ZZfishing.api.profile.service.ProfileService;
import com.example.ZZfishing.model.exception.IdNotValidException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProfileControllerTest {

    private final ProfileRepository repository = Mockito.mock(ProfileRepository.class);
    private final ApplicationEventPublisher publisher = Mockito.mock(ApplicationEventPublisher.class);
    private final ProfileMapper profileMapper = Mockito.mock(ProfileMapper.class);
    private final ProfileService profileService = new ProfileService(repository, publisher, profileMapper);

    private static final Long VALID_ID = 1L;
    private static final Long INVALID_ID = -1L;

    @Test
    void canDeleteProfileById() {
        long id = VALID_ID;

        given(repository.existsById(id))
                .willReturn(true);
        repository.deleteById(id);
        verify(repository).deleteById(id);
    }

    @Test
    void canDeleteProfileByIdAndCheckDeletedProfile() {
        long id = VALID_ID;
        Profile profile = getProfile(id);

        given(repository.existsById(id))
                .willReturn(true);
        repository.deleteById(id);

        Optional<Profile> optionalProfile = repository.findById(id);
        assertThat(optionalProfile).isEmpty();
    }

    @Test
    void canGetAllProfiles() {
        Profile profile = getProfile(VALID_ID);
        when(repository.findAll())
                .thenReturn(Stream.of(profile).collect(Collectors.toList()));
        assertEquals(1, profileService.getProfiles().size());
    }

    @Test
    void canGetProfileById() {
        Profile profile = getProfile(VALID_ID);
        when(repository.findById(VALID_ID))
                .thenReturn(Optional.of(profile));
        assertEquals(profile, profileService.getProfileById(VALID_ID));
    }

    @Test
    void canGetProfileByInvalidId() {
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
        profile.setFirstName("Alfred");
        return profile;
    }
}