package com.example.ZZfishing.api.user.controller;

import com.example.ZZfishing.api.user.service.ProgramUserService;
import com.example.ZZfishing.api.user.repository.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/programUser")
public class ProgramUserController {

    private final ProgramUserService programUserService;

    public ProgramUserController(ProgramUserService programUserService) {
        this.programUserService = programUserService;
    }

    @GetMapping
    @Operation(
            summary = "Fetch all programUsers",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "All programUsers fetched"),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Unable to fetch programUsers due to internal error")
                    })
    public ResponseEntity<List<User>> getProgramUsers() {
        List<User> programUsers = programUserService.getProgramUsers().stream().toList();
        HttpStatus httpStatus = HttpStatus.OK;
        return new ResponseEntity<>(programUsers, httpStatus);
    }

    @GetMapping("{programUserId}")
    public User fetchProgramUserById(
            @PathVariable("programUserId") Long programUserId) {
                return programUserService.fetchProgramUserById(programUserId);
    }

    @Operation(
            summary = "Create order",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "A programUser has been created"),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Unable to create a new programUser due to internal error"
                    )}
    )
    @PostMapping
    public ResponseEntity<User> registerNewProgramUser(@RequestBody User programUser) {
        User returnProgramUser = programUserService.addNewProgramUser(programUser);
        HttpStatus httpStatus = HttpStatus.CREATED;
        return new ResponseEntity<>(returnProgramUser, httpStatus);
    }

    @DeleteMapping(path = "{programUserId}")
    public ResponseEntity<User> deleteProgramUser(@PathVariable("programUserId") Long programUserId) {
        programUserService.deleteProgramUser(programUserId);
        HttpStatus httpStatus = HttpStatus.OK;
        return new ResponseEntity<>(httpStatus);
    }

    @PutMapping(path = "{programUserId}")
    public ResponseEntity<User> updateProgramUser(
            @PathVariable ("programUserId") Long programUserId,
            @RequestBody User programUser) {
                User returnProgramUser = programUserService.updateProgramUser(programUserId, programUser);
                HttpStatus httpStatus = HttpStatus.OK;
                return new ResponseEntity<>(returnProgramUser, httpStatus);
    }
}
