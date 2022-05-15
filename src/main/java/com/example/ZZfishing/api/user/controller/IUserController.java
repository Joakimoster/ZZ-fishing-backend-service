package com.example.ZZfishing.api.user.controller;


import com.example.ZZfishing.api.user.repository.entity.User;
import com.example.ZZfishing.api.user.repository.entity.dto.UserRequestBodyDto;
import com.example.ZZfishing.api.user.repository.entity.dto.UserResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUserController {
    ResponseEntity<List<User>> getUsers();

    ResponseEntity<User> getUserById(Long id);

    ResponseEntity<UserResponseDto> registerNewUser(UserRequestBodyDto user);

    ResponseEntity<User> deleteUser(Long id);

    ResponseEntity<User> updateUser(Long id, User user);
}
