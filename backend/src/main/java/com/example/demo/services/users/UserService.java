package com.example.demo.services.users;

import com.example.demo.repositories.users.UserRepository;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    @Qualifier("userRepository")
    private UserRepository userRepository;

    public User validateUser(User user) {
        return userRepository.validateUser(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.getUsers();
    }
}
