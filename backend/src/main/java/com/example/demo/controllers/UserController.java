package com.example.demo.controllers;
import com.example.demo.services.users.IUserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import model.Comercio;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/users")
    public List<User> getUsers() throws JsonProcessingException {
        return userService.getUsers();
    }

    @PostMapping(path = "/validateUser", consumes = "application/json", produces = "application/json")
    public User validateUser(@RequestBody User user) {
        return userService.validateUser(user);
    }

}
