package com.example.ZZfishing.api.programuser.controller;

import com.example.ZZfishing.api.profile.repository.entity.Profile;
import com.example.ZZfishing.api.programuser.exception.ProgramUserNotFoundException;
import com.example.ZZfishing.api.programuser.repository.entity.ProgramUser;
import com.example.ZZfishing.api.programuser.service.ProgramUserService;
import com.example.ZZfishing.model.ResponseMessageDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("junit")
@WebMvcTest({ProgramUserController.class})
class ProgramUserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ProgramUserService programUserService;

    @Autowired
    private ProgramUserController programUserController;

    private static final Long VALID_ID = 1L;


    @Test
    void canGetAllProgramUsers() throws Exception {
        List<ProgramUser> programUsersList = List.of(
                getProgramUser(VALID_ID)
        );

        when(programUserService.getProgramUsers()).thenReturn(programUsersList);

        mockMvc.perform(
                get("/api/v1/programUser"))
                .andExpect(status().isOk())
                .andDo(print());
    }
    @Test
    void canGetProgramUserById() throws Exception {
        ProgramUser programUser = getProgramUser(VALID_ID);
        when(programUserService.fetchProgramUserById(1L))
                .thenReturn(programUser);
        mockMvc.perform(
                get("/api/v1/programUser/{id}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    void canThrowProgramUserIdNotFoundException() throws Exception {



        /*ProgramUser programUser = getProgramUser(VALID_ID);

        when(programUserService.fetchProgramUserById(2L))
                .thenThrow(ProgramUserNotFoundException.class);
        //doThrow(new ProgramUserNotFoundException(1L)).when(programUserService).fetchProgramUserById(1L);

        mockMvc.perform(get("/api/v1/programUser/{id}", 2L))
                .andExpect(status().isNotFound());*/
    }

    @Test
    public void findAllProgramUsers_invalidRequest_throwNoDataFound() throws Throwable {
        Assertions.assertThatThrownBy(() ->
                        mockMvc.perform(get("/api/v1/programUser/{id}")).andExpect(status().isInternalServerError())
                                .andExpect(status().is4xxClientError())
                                .andExpect(content().string("{\"error\":\"not found\"}")));
    }

    /*@Test
    void canThrowProgramUserIdNotFoundException() throws Exception {
        //doThrow(ProgramUserNotFoundException.class).when(programUserService).fetchProgramUserById(anyLong());
        //doThrow(PROGRAMUSER_NOT_FOUND_EXCEPTION).when(programUserService.fetchProgramUserById(1L));
        //String exceptionParam = "not_found";
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/programUser/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ProgramUserNotFoundException))
                .andExpect(result -> assertEquals("bad arguments", Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }*/

    @Test
    void canDeleteProgramUser() throws Exception {
        ProgramUser programUser = getProgramUser(VALID_ID);
        when(programUserService.fetchProgramUserById(VALID_ID))
                .thenReturn(programUser);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/programUser/{id}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    void canAddNewProgramUser() throws Exception {
        ProgramUser programUser = getProgramUser(VALID_ID);

        when(programUserService.addNewProgramUser(programUser))
                .thenReturn(programUser);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/programUser")
                .contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isCreated());
    }

    private static ProgramUser getProgramUser(Long id) {
        ProgramUser programUser = new ProgramUser();
        programUser.setEmail("Apaserdig@hotmail.com");
        programUser.setFirstName("Hanses");
        programUser.setLastName("Fremmet");
        programUser.setPassword("Hej123");
        programUser.setProfile(new Profile());
        programUser.setId(id);
        return programUser;
    }
}