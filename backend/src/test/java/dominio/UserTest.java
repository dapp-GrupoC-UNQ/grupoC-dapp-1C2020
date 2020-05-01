package dominio;

import model.User;
import builders.UserBuilder;
import model.excepciones.InvalidUsernameOrPasswordException;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserTest {

    @Test
    public void clienteTieneUnUsuario(){
        User pepe = UserBuilder.user().withUsername("pepe1234").build();
        assertEquals(pepe.username(), "pepe1234");
    }

    @Test
    public void clientCannotHaveAnEmptyUsername(){
        assertThrows(InvalidUsernameOrPasswordException.class , ()->  UserBuilder.user().withUsername("").build() );
    }

    @Test
    public void clientCannotHaveAnEmptyPassword(){
        assertThrows(InvalidUsernameOrPasswordException.class , ()->  UserBuilder.user().withPassword("").build() );
    }
}
