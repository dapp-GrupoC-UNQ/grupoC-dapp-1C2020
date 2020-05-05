package com.example.demo.services;

import com.example.demo.builders.UserBuilder;
import com.example.demo.model.excepciones.NotAvailableUserNameException;
import com.example.demo.model.excepciones.NotFoundUserException;
import com.example.demo.repositories.users.UserRepository;
import com.example.demo.services.users.IUserService;
import com.example.demo.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @MockBean
    UserRepository userRepositoryMock; //Si esto esta definido hay que usarlo si o si en cada test

    @Autowired
    IUserService userService;

    @Test
    public void aUserIsValidIfItExistsAndItsUsernameMatchesItsPassword() {
        User user = UserBuilder.user().build();
        when(userRepositoryMock.validateUser(any())).thenReturn(user);

        assertEquals(userService.validateUser(user).username(), user.username());
    }

    @Test
    public void aUserIsNotValidIfItsNotRegistered() {
        User user = UserBuilder.user().build();
        List<User> fakeUsersList = Arrays.asList(UserBuilder.user().withUsername("FakeUser").build());
        when(userRepositoryMock.validateUser(any())).thenThrow(new NotFoundUserException());

        assertThrows(NotFoundUserException.class, () -> userService.validateUser(user));

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
        User user = UserBuilder.user().build();
        when(userRepositoryMock.addUser(any(), any())).thenReturn(user);

        assertEquals(userService.addUser(user.username(), user.password()), user);
    }

    @Test
    public void whenTryingToAddUserWithOccupiedUsernameNotAvailableNameExceptionIsThrown(){
        when(userRepositoryMock.addUser(any(), any())).thenThrow(new NotAvailableUserNameException());

        assertThrows(NotAvailableUserNameException.class, () -> userService.addUser("aNewUser", "password"));
    }
}
