package com.example.ZZfishing.api.user.service;

import com.example.ZZfishing.api.user.repository.entity.User;

import java.util.List;

public interface IUserService {
    List<User> getUsers();

    User addNewUser(User user);

    void deleteUser(Long userId);

    User updateUser(Long userId, User user);

    User getUserById(Long userId);
}
