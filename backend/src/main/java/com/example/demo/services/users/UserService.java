package com.example.demo.services.users;

import com.example.demo.model.store.Store;
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

    public User validateUser(User user) {
        return userRepository.validateUser(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.getUsers();
    }

    @Override
    public Boolean canAddUser(String username) {
        return userRepository.canAddUser(username);
    }

    @Override
    public ClientUser addUser(String username, String password) {
        return userRepository.addUser(username, password);
    }

    @Override
    public StoreAdminUser addStoreAdmin(StoreAdminUser storeAdminUser) {
        return userRepository.addStoreAdmin(storeAdminUser.username(), storeAdminUser.password(), storeAdminUser.store());
    }
}
