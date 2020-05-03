package com.example.demo.repositories.users;


import com.example.demo.model.User;
import com.example.demo.model.excepciones.NotFoundUserException;
import org.springframework.stereotype.Repository;


import java.util.Arrays;
import java.util.List;

@Repository
public class UserRepository {

    private List<User> registeredUsers = Arrays.asList(new User("Pepe", "abcdefg"),
            new User("John", "123456"),
            new User("More", "fafafa"));

    public List<User> getUsers() { return this.registeredUsers; }
    public User validateUser(User aUser) {
        return this.getUsers().stream().filter(user -> user.username().equals(aUser.username()) && user.password().equals(aUser.password()))
                                        .findFirst()
                                        .orElseThrow(NotFoundUserException::new);
    }
}
