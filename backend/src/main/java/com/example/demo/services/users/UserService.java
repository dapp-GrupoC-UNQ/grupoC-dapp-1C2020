package com.example.demo.services.users;

import com.example.demo.model.exceptions.NotAvailableUserNameException;
import com.example.demo.model.exceptions.NotFoundUserException;
import com.example.demo.model.user.StoreAdminUser;
import com.example.demo.model.user.User;
import com.example.demo.repositories.users.UserRepository;
import com.example.demo.model.user.ClientUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    public User authenticateUser(User user) {
        return userRepository.findByUsernameAndPassword(user.username(), user.password())
                .orElseThrow(NotFoundUserException::new);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public Boolean canAddUser(String username) {
        if(userRepository.findByUsernameEquals(username).isPresent()) {
            throw new NotAvailableUserNameException();
        }
        else return true;
    }

    @Override
    public ClientUser addUser(String username, String password) {
        if(canAddUser(username)) {
            ClientUser newClientUser = new ClientUser(username, password);
            userRepository.save(newClientUser);
            return newClientUser;
        }
        throw new NotAvailableUserNameException();
    }

    @Override
    public StoreAdminUser addStoreAdmin(StoreAdminUser storeAdminUser) {
        if(canAddUser(storeAdminUser.username())) {
            StoreAdminUser newClientUser = new StoreAdminUser(storeAdminUser.username(), storeAdminUser.password(), storeAdminUser.store());
            userRepository.save(newClientUser);
            return newClientUser;
        }
        throw new NotAvailableUserNameException();
    }
}
