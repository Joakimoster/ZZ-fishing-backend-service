package com.example.ZZfishing.api.user.mapper;

import com.example.ZZfishing.api.user.repository.entity.User;
import com.example.ZZfishing.api.user.repository.entity.dto.UserRequestBodyDto;
import com.example.ZZfishing.api.user.repository.entity.dto.UserResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponseDto userToResponseUserDto(User user);

    User userResponseDtoToUser(UserResponseDto dto);

    UserRequestBodyDto userToRequestBodyDto(User user);

    @Mapping(source = "profileId", target = "profile.id")
    User requestBodyDtoToUser(UserRequestBodyDto dto);
}
