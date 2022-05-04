package com.example.ZZfishing.api.user.controller;


import com.example.ZZfishing.api.user.repository.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUserController {
    ResponseEntity<List<User>> getUsers();

    ResponseEntity<User> getUserById(Long id);

    ResponseEntity<User> registerNewUser(User user);

    ResponseEntity<User> deleteUser(Long id);

    ResponseEntity<User> updateUser(Long id, User user);
}
