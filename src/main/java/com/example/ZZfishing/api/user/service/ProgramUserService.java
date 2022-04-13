package com.example.ZZfishing.api.user.service;

import com.example.ZZfishing.api.user.exception.ProgramUserNotDeletedException;
import com.example.ZZfishing.api.user.exception.ProgramUserNotFoundException;
import com.example.ZZfishing.api.user.repository.ProgramUserRepository;
import com.example.ZZfishing.api.user.repository.entity.User;
import com.example.ZZfishing.utils.IdUtil;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ProgramUserService {

    private final ProgramUserRepository programUserRepository;

    public ProgramUserService(
            ProgramUserRepository programUserRepository,
            ApplicationEventPublisher publisher) {
                this.programUserRepository = programUserRepository;
    }

    public List<User> getProgramUsers() {
        return programUserRepository.findAll();
    }

    public User addNewProgramUser(User programUser) {
        return programUserRepository.save(programUser);
    }

    public void deleteProgramUser(Long programUserId) {
        boolean exists = programUserRepository.existsById(programUserId);

        if (!exists) {
            throw new ProgramUserNotDeletedException(programUserId);
        }
        programUserRepository.deleteById(programUserId);
    }

    public User updateProgramUser(Long programUserId, User programUser) {
        User programUserDB = programUserRepository.findById(programUserId)
                .orElseThrow(() -> new ProgramUserNotFoundException(programUserId));

        if (Objects.nonNull(programUser.getFirstName()) &&
                !"".equalsIgnoreCase(programUser.getFirstName())) {
                programUserDB.setFirstName(programUser.getFirstName());
        }
        if (Objects.nonNull(programUser.getLastName()) &&
                !"".equalsIgnoreCase(programUser.getLastName())) {
                programUserDB.setLastName(programUser.getLastName());
        }
        if (Objects.nonNull(programUser.getPassword()) &&
                !"".equalsIgnoreCase(programUser.getPassword())) {
                programUserDB.setPassword(programUser.getPassword());
        }
        if (Objects.nonNull(programUser.getEmail()) &&
                !"".equalsIgnoreCase(programUser.getEmail())) {
                programUserDB.setEmail(programUser.getEmail());
        }
        return programUserRepository.save(programUser);
    }

    public User fetchProgramUserById(Long programUserId) {
        IdUtil.assertId(programUserId);
        return programUserRepository.findById(programUserId).orElseThrow(
                () -> new ProgramUserNotFoundException(programUserId));
    }
}
