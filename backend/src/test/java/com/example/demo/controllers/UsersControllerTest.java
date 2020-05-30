package com.example.demo.controllers;

import com.example.demo.builders.UserBuilder;
import com.example.demo.model.exceptions.NotFoundUserException;
import com.example.demo.services.users.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.demo.model.ClientUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UsersControllerTest {

    @MockBean
    UserService userServiceMock;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void aUserIsValidatedIfItsUsernameMatchesWithItsPassword() throws Exception {
        ClientUser clientUser = UserBuilder.user().build();
        when(userServiceMock.validateUser(any())).thenReturn(clientUser);

        mockMvc.perform(post("/validateUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(clientUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("username", is(clientUser.username())));
    }

    @Test
    public void aUserIsNotValidatedIfItsUsernameMatchesWithItsPassword() throws Exception {
        ClientUser clientUser = UserBuilder.user().build();
        when(userServiceMock.validateUser(any())).thenThrow(new NotFoundUserException());

        mockMvc.perform(post("/validateUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(clientUser)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void whenCreatingAUserTheUserIsReturnedAndTheStatusIsOK() throws Exception {
        ClientUser clientUser = UserBuilder.user().build();
        when(userServiceMock.addUser(any(), any())).thenReturn(clientUser);

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(clientUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("username", is(clientUser.username())));
    }
}
