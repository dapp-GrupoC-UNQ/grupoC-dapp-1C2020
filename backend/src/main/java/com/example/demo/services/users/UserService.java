package com.example.demo.services.users;

import com.example.demo.repositories.users.UserRepository;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    public User validateUser(User user) {
        return new User("Saracatunga", "1234");
    }
}
