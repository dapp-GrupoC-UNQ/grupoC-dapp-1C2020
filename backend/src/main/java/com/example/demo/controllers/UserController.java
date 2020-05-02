package com.example.demo.controllers;
import com.example.demo.services.users.IUserService;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping(path = "/validateUser", consumes = "application/json", produces = "application/json")
    public User validateUser(@RequestBody User user) {
        return userService.validateUser(user);
    }

}
