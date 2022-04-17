package com.example.ZZfishing.api.user.controller;

import com.example.ZZfishing.api.user.service.UserService;
import com.example.ZZfishing.api.user.repository.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @Operation(
            summary = "Fetch all users",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "All users fetched"),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Unable to fetch users due to internal error")
                    })
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.getUsers().stream().toList();
        HttpStatus httpStatus = HttpStatus.OK;
        return new ResponseEntity<>(users, httpStatus);
    }

    @GetMapping("{userId}")
    public User fetchUserById(
            @PathVariable("userId") Long userId) {
                return userService.fetchUserById(userId);
    }

    @Operation(
            summary = "Create order",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "A user has been created"),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Unable to create a new user due to internal error"
                    )}
    )
    @PostMapping
    public ResponseEntity<User> registerNewUser(@RequestBody User user) {
        User returnUser = userService.addNewUser(user);
        HttpStatus httpStatus = HttpStatus.CREATED;
        return new ResponseEntity<>(returnUser, httpStatus);
    }

    @DeleteMapping(path = "{userId}")
    public ResponseEntity<User> deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
        HttpStatus httpStatus = HttpStatus.OK;
        return new ResponseEntity<>(httpStatus);
    }

    @PutMapping(path = "{userId}")
    public ResponseEntity<User> updateUser(
            @PathVariable ("userId") Long userId,
            @RequestBody User user) {
                User returnUser = userService.updateUser(userId, user);
                HttpStatus httpStatus = HttpStatus.OK;
                return new ResponseEntity<>(returnUser, httpStatus);
    }
}
