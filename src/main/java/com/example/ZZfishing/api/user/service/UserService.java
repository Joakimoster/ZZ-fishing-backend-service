package com.example.ZZfishing.api.user.service;

import com.example.ZZfishing.api.user.exception.UserNotDeletedException;
import com.example.ZZfishing.api.user.exception.UserNotFoundException;
import com.example.ZZfishing.api.user.mapper.UserMapper;
import com.example.ZZfishing.api.user.repository.UserRepository;
import com.example.ZZfishing.api.user.repository.entity.User;
import com.example.ZZfishing.api.user.repository.entity.dto.UserRequestBodyDto;
import com.example.ZZfishing.api.user.repository.entity.dto.UserResponseDto;
import com.example.ZZfishing.utils.IdUtil;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;

    public UserService(
            UserRepository userRepository,
            ApplicationEventPublisher publisher, UserMapper mapper) {
                this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserResponseDto addNewUser(UserRequestBodyDto user) {
        return mapper.userToResponseUserDto(userRepository.save(mapper.requestBodyDtoToUser(user)));
    }

    @Override
    public void deleteUser(Long userId) {
        boolean exists = userRepository.existsById(userId);

        if (!exists) {
            throw new UserNotDeletedException(userId);
        }
        userRepository.deleteById(userId);
    }

    @Override
    public User updateUser(Long userId, User user) {
        User userDB = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

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

    @Override
    public User getUserById(Long userId) {
        IdUtil.assertId(userId);
        return userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(userId));
    }
}
