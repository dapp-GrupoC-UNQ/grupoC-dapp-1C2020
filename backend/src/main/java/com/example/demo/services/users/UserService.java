package com.example.demo.services.users;

import com.example.demo.repositories.users.UserRepository;
import com.example.demo.model.ClientUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    public ClientUser validateUser(ClientUser clientUser) {
        return userRepository.validateUser(clientUser);
    }

    @Override
    public List<ClientUser> getUsers() {
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
}
