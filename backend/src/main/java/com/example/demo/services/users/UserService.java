package com.example.demo.services.users;

import com.example.demo.repositories.users.UserRepository;
import com.example.demo.model.User;
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
    public User addUser(String username, String password) {
        return userRepository.addUser(username, password);
    }
}
