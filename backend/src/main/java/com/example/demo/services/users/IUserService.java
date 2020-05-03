package com.example.demo.services.users;

import model.User;

import java.util.List;

public interface IUserService {

    User validateUser(User user);

    List<User> getUsers();
}
