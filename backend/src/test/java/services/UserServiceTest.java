package services;

import builders.UserBuilder;
import com.example.demo.repositories.users.UserRepository;
import com.example.demo.services.users.UserService;
import model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepositoryMock;

    @InjectMocks
    UserService userService;

    @Test
    public void aUserIsValidIfItExistsAndItsUsernameMatchesItsPassword() {
        User user = UserBuilder.user().build();
        when(userRepositoryMock.validateUser(any())).thenReturn(user);

        assertEquals(userService.validateUser(user).username(), user.username());
    }
}
