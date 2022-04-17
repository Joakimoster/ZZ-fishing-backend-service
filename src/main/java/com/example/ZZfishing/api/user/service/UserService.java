package com.example.ZZfishing.api.user.service;

import com.example.ZZfishing.api.user.exception.UserNotDeletedException;
import com.example.ZZfishing.api.user.exception.UserNotFoundException;
import com.example.ZZfishing.api.user.repository.UserRepository;
import com.example.ZZfishing.api.user.repository.entity.User;
import com.example.ZZfishing.utils.IdUtil;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(
            UserRepository userRepository,
            ApplicationEventPublisher publisher) {
                this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User addNewUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        boolean exists = userRepository.existsById(userId);

        if (!exists) {
            throw new UserNotDeletedException(userId);
        }
        userRepository.deleteById(userId);
    }

    public User updateUser(Long userId, User user) {
        User userDB = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        if (Objects.nonNull(user.getFirstName()) &&
                !"".equalsIgnoreCase(user.getFirstName())) {
                userDB.setFirstName(user.getFirstName());
        }
        if (Objects.nonNull(user.getLastName()) &&
                !"".equalsIgnoreCase(user.getLastName())) {
                userDB.setLastName(user.getLastName());
        }
        if (Objects.nonNull(user.getPassword()) &&
                !"".equalsIgnoreCase(user.getPassword())) {
                userDB.setPassword(user.getPassword());
        }
        if (Objects.nonNull(user.getEmail()) &&
                !"".equalsIgnoreCase(user.getEmail())) {
                userDB.setEmail(user.getEmail());
        }
        return userRepository.save(user);
    }

    public User fetchUserById(Long userId) {
        IdUtil.assertId(userId);
        return userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(userId));
    }
}
