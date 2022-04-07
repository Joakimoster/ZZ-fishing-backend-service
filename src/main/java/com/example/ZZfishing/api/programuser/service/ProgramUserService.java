package com.example.ZZfishing.api.programuser.service;

import com.example.ZZfishing.api.programuser.exception.ProgramUserNotDeletedException;
import com.example.ZZfishing.api.programuser.exception.ProgramUserNotFoundException;
import com.example.ZZfishing.api.programuser.repository.ProgramUserRepository;
import com.example.ZZfishing.api.programuser.repository.entity.ProgramUser;
import com.example.ZZfishing.utils.IdUtil;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
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

    public List<ProgramUser> getProgramUsers() {
        return programUserRepository.findAll();
    }

    public ProgramUser addNewProgramUser(ProgramUser programUser) {
        return programUserRepository.save(programUser);
    }

    public void deleteProgramUser(Long programUserId) {
        boolean exists = programUserRepository.existsById(programUserId);

        if (!exists) {
            throw new ProgramUserNotDeletedException(programUserId);
        }
        programUserRepository.deleteById(programUserId);
    }

    public ProgramUser updateProgramUser(Long programUserId, ProgramUser programUser) {
        ProgramUser programUserDB = programUserRepository.findById(programUserId)
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

    public ProgramUser fetchProgramUserById(Long programUserId) {
        IdUtil.assertId(programUserId);
        return programUserRepository.findById(programUserId).orElseThrow(
                () -> new ProgramUserNotFoundException(programUserId));
    }
}
