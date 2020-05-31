package com.example.demo.repositories.users;


import com.example.demo.model.store.Store;
import com.example.demo.model.user.ClientUser;
import com.example.demo.model.exceptions.NotAvailableUserNameException;
import com.example.demo.model.exceptions.NotFoundUserException;
import com.example.demo.model.user.StoreAdminUser;
import com.example.demo.model.user.User;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    private List<User> registeredClientUsers = new ArrayList<>();


    public List<User> getUsers() { return this.registeredClientUsers; }

    public User validateUser(User aUser) {
        return this.getUsers().stream().filter(user -> user.username().equals(aUser.username()) && user.password().equals(aUser.password()))
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

    public StoreAdminUser addStoreAdmin(String username, String password, Store store) {
        if(canAddUser(username)) {
            StoreAdminUser newStoreAdmin = new StoreAdminUser(username, password, store);
            this.registeredClientUsers.add(newStoreAdmin);
            return newStoreAdmin;
        }
        throw new NotAvailableUserNameException();
    };
}
