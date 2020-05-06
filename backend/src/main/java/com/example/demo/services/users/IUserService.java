package com.example.demo.services.users;

import com.example.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface IUserService {

    User validateUser(User user);

    List<User> getUsers();

    Boolean canAddUser(String username);

    User addUser(String username, String password);
}
