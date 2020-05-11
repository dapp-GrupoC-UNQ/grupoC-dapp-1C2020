package com.example.demo.repositories.users;


import com.example.demo.model.User;
import com.example.demo.model.exceptions.NotAvailableUserNameException;
import com.example.demo.model.exceptions.NotFoundUserException;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    private List<User> registeredUsers = new ArrayList<>();


    public List<User> getUsers() { return this.registeredUsers; }

    public User validateUser(User aUser) {
        return this.getUsers().stream().filter(user -> user.username().equals(aUser.username()) && user.password().equals(aUser.password()))
                                        .findFirst()
                                        .orElseThrow(NotFoundUserException::new);
    }

    public Boolean canAddUser(String username) {
        return this.getUsers().stream().allMatch(user -> !user.username().equals(username));
    }

    public User addUser(String username, String password) {
        if(canAddUser(username)) {
            User newUser = new User(username, password);
            this.registeredUsers.add(newUser);
            return newUser;
        }
        throw new NotAvailableUserNameException();
    }
}
