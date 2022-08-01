package com.example.ZZfishing.api.user.service;

import com.example.ZZfishing.api.user.repository.entity.User;
import com.example.ZZfishing.api.user.repository.entity.dto.UserRequestBodyDto;
import com.example.ZZfishing.api.user.repository.entity.dto.UserResponseDto;

import java.util.List;

public interface IUserService {
    List<User> getUsers();

    UserResponseDto addNewUser(UserRequestBodyDto user);

    void deleteUser(Long userId);

    User updateUser(Long userId, User user);

    User getUserById(Long userId);
}
