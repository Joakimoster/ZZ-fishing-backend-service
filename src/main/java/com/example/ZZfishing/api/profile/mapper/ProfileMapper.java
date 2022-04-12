package com.example.ZZfishing.api.profile.mapper;

import com.example.ZZfishing.api.profile.controller.dto.ProfileDto;
import com.example.ZZfishing.api.profile.repository.entity.Profile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

    ProfileDto toProfileDto(Profile profile);
}
