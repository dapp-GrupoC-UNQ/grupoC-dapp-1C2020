package com.example.demo.model.user;

import com.example.demo.builders.ClientUserBuilder;
import com.example.demo.model.ClientUser;
import com.example.demo.model.exceptions.InvalidUsernameOrPasswordException;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClientUserTest {

    @Test
    public void clientKnowsItsUsername(){
        ClientUser pepe = ClientUserBuilder.user().withUsername("pepe1234").build();
        assertEquals(pepe.username(), "pepe1234");
    }

    @Test
    public void clientKnowsItsPassword(){
        ClientUser pepe = ClientUserBuilder.user().withPassword("pepe1234").build();
        assertEquals(pepe.password(), "pepe1234");
    }

    @Test
    public void clientCannotHaveAnEmptyUsername(){
        assertThrows(InvalidUsernameOrPasswordException.class , ()->  ClientUserBuilder.user().withUsername("").build() );
    }

    @Test
    public void clientCannotHaveAnEmptyPassword(){
        assertThrows(InvalidUsernameOrPasswordException.class , ()->  ClientUserBuilder.user().withPassword("").build() );
    }

    @Test
    public void aNormalUserIsNotAStoreAdmin() {
        ClientUser pepe = ClientUserBuilder.user().build();
        assertFalse(pepe.isAdminOfStore());
    }
}
