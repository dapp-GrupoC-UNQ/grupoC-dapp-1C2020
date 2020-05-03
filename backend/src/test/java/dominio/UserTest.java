package dominio;

import model.User;
import builders.UserBuilder;
import model.excepciones.InvalidUsernameOrPasswordException;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void clientKnowsItsUsername(){
        User pepe = UserBuilder.user().withUsername("pepe1234").build();
        assertEquals(pepe.username(), "pepe1234");
    }

    @Test
    public void clientKnowsItsPassword(){
        User pepe = UserBuilder.user().withPassword("pepe1234").build();
        assertEquals(pepe.password(), "pepe1234");
    }

    @Test
    public void clientCannotHaveAnEmptyUsername(){
        assertThrows(InvalidUsernameOrPasswordException.class , ()->  UserBuilder.user().withUsername("").build() );
    }

    @Test
    public void clientCannotHaveAnEmptyPassword(){
        assertThrows(InvalidUsernameOrPasswordException.class , ()->  UserBuilder.user().withPassword("").build() );
    }

    @Test
    public void aNormalUserIsNotAStoreAdmin() {
        User pepe = UserBuilder.user().build();
        assertFalse(pepe.isAdminOfStore());
    }
}
