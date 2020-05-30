package com.example.demo.repositories.users;


import com.example.demo.model.user.ClientUser;
import com.example.demo.model.exceptions.NotAvailableUserNameException;
import com.example.demo.model.exceptions.NotFoundUserException;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    private List<ClientUser> registeredClientUsers = new ArrayList<>();


    public List<ClientUser> getUsers() { return this.registeredClientUsers; }

    public ClientUser validateUser(ClientUser aClientUser) {
        return this.getUsers().stream().filter(user -> user.username().equals(aClientUser.username()) && user.password().equals(aClientUser.password()))
                                        .findFirst()
                                        .orElseThrow(NotFoundUserException::new);
    }

    public Boolean canAddUser(String username) {
        return this.getUsers().stream().allMatch(user -> !user.username().equals(username));
    }

    public ClientUser addUser(String username, String password) {
        if(canAddUser(username)) {
            ClientUser newClientUser = new ClientUser(username, password);
            this.registeredClientUsers.add(newClientUser);
            return newClientUser;
        }
        throw new NotAvailableUserNameException();
    }
}
