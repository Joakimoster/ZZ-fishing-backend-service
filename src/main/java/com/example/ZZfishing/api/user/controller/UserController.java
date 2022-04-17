package com.example.ZZfishing.api.user.controller;

import com.example.ZZfishing.api.user.service.UserService;
import com.example.ZZfishing.api.user.repository.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Get user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User fetched by id"),
            @ApiResponse(responseCode = "500", description = "Unable to fetch user due to internal error")
    })
    public ResponseEntity<User> fetchUserById(
            @PathVariable("userId") Long userId) {
                User userDB = userService.fetchUserById(userId);
                HttpStatus httpStatus = HttpStatus.OK;
                return new ResponseEntity<>(userDB, httpStatus);
    }

    @PostMapping
    @Operation(summary = "Register a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "New user was successfully created"),
            @ApiResponse(responseCode = "500", description = "Unable to create a new user due to internal error")
    })
    public ResponseEntity<User> registerNewUser(@RequestBody User user) {
        User returnUser = userService.addNewUser(user);
        HttpStatus httpStatus = HttpStatus.CREATED;
        return new ResponseEntity<>(returnUser, httpStatus);
    }

    @DeleteMapping(path = "{userId}")
    @Operation(summary = "Delete user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User was successfully deleted"),
            @ApiResponse(responseCode = "500", description = "Unable to delete the user due to internal error")
    })
    public ResponseEntity<User> deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
        HttpStatus httpStatus = HttpStatus.OK;
        return new ResponseEntity<>(httpStatus);
    }

    @PutMapping(path = "{userId}")
    @Operation(summary = "Update user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User was successfully updated"),
            @ApiResponse(responseCode = "500", description = "Unable to update the user due to internal error")
    })
    public ResponseEntity<User> updateUser(
            @PathVariable ("userId") Long userId,
            @RequestBody User user) {
                User returnUser = userService.updateUser(userId, user);
                HttpStatus httpStatus = HttpStatus.OK;
                return new ResponseEntity<>(returnUser, httpStatus);
    }
}