package com.example.demo.services;

import com.example.demo.builders.UserBuilder;
import com.example.demo.model.exceptions.NotAvailableUserNameException;
import com.example.demo.model.exceptions.NotFoundUserException;
import com.example.demo.repositories.users.UserRepository;
import com.example.demo.services.users.IUserService;
import com.example.demo.model.ClientUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class ClientUserServiceTest {

    @MockBean
    UserRepository userRepositoryMock; //Si esto esta definido hay que usarlo si o si en cada test

    @Autowired
    IUserService userService;

    @Test
    public void aUserIsValidIfItExistsAndItsUsernameMatchesItsPassword() {
        ClientUser clientUser = UserBuilder.user().build();
        when(userRepositoryMock.validateUser(any())).thenReturn(clientUser);

        assertEquals(userService.validateUser(clientUser).username(), clientUser.username());
    }

    @Test
    public void aUserIsNotValidIfItsNotRegistered() {
        ClientUser clientUser = UserBuilder.user().build();
        List<ClientUser> fakeUsersList = Arrays.asList(UserBuilder.user().withUsername("FakeUser").build());
        when(userRepositoryMock.validateUser(any())).thenThrow(new NotFoundUserException());

        assertThrows(NotFoundUserException.class, () -> userService.validateUser(clientUser));

    }

    @Test
    public void aUserCanBeAddedIfTheresNotAnotherRegisteredUSerWithThatUsername() {
        when(userRepositoryMock.canAddUser(any())).thenReturn(true);
        assertTrue(userService.canAddUser("aNewUser"));
    }

    @Test
    public void aUserCannotBeAddedIfTheresAnotherRegisteredUSerWithThatUsername() {
        when(userRepositoryMock.canAddUser(any())).thenReturn(false);
        assertFalse(userService.canAddUser("aNewUser"));
    }

    @Test
    public void whenAUserIsAddedTheServiceReturnsTheUser(){
        ClientUser clientUser = UserBuilder.user().build();
        when(userRepositoryMock.addUser(any(), any())).thenReturn(clientUser);

        assertEquals(userService.addUser(clientUser.username(), clientUser.password()), clientUser);
    }

    @Test
    public void whenTryingToAddUserWithOccupiedUsernameNotAvailableNameExceptionIsThrown(){
        when(userRepositoryMock.addUser(any(), any())).thenThrow(new NotAvailableUserNameException());

        assertThrows(NotAvailableUserNameException.class, () -> userService.addUser("aNewUser", "password"));
    }
}
