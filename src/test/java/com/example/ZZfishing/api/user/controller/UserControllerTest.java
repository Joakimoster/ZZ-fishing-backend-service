package com.example.ZZfishing.api.user.controller;

import com.example.ZZfishing.api.profile.repository.entity.Profile;
import com.example.ZZfishing.api.user.repository.entity.User;
import com.example.ZZfishing.api.user.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("junit")
@WebMvcTest({UserController.class})
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    @Autowired
    private UserController userController;

    private static final Long VALID_ID = 1L;

    @Test
    void canGetAllUsers() throws Exception {
        List<User> userList = List.of(
                getUser(VALID_ID)
        );

        when(userService.getUsers()).thenReturn(userList);

        mockMvc.perform(
                get("/api/v1/user"))
                .andExpect(status().isOk())
                .andDo(print());
    }
    @Test
    void canGetUserById() throws Exception {
        User user = getUser(VALID_ID);
        when(userService.getUserById(1L))
                .thenReturn(user);
        mockMvc.perform(
                get("/api/v1/user/{id}", 1L))
                .andExpect(status().isOk());
    }

    /*@Test
    void canThrowProgramUserIdNotFoundException() throws Exception {
        ProgramUser programUser = getProgramUser(VALID_ID);

        when(programUserService.fetchProgramUserById(2L))
                .thenThrow(ProgramUserNotFoundException.class);
        //doThrow(new ProgramUserNotFoundException(1L)).when(programUserService).fetchProgramUserById(1L);

        mockMvc.perform(get("/api/v1/programUser/{id}", 2L))
                .andExpect(status().isNotFound());
    }*/

    @Test
    public void findAllUsers_invalidRequest_throwNoDataFound() throws Throwable {
        Assertions.assertThatThrownBy(() ->
                        mockMvc.perform(get("/api/v1/user/{id}")).andExpect(status().isInternalServerError())
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
    void canDeleteUser() throws Exception {
        User user = getUser(VALID_ID);
        when(userService.getUserById(VALID_ID))
                .thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/user/{id}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    void canAddNewUser() throws Exception {
        User user = getUser(VALID_ID);

        when(userService.addNewUser(user))
                .thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user")
                .contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isCreated());
    }

    private static User getUser(Long id) {
        User user = new User();
        user.setEmail("Apaserdig@hotmail.com");
        user.setFirstName("Hanses");
        user.setLastName("Fremmet");
        user.setPassword("Hej123");
        user.setProfile(new Profile());
        user.setId(id);
        return user;
    }
}