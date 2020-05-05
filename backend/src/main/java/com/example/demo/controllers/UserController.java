package com.example.demo.controllers;
import com.example.demo.services.users.IUserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/users")
    public List<User> getUsers() throws JsonProcessingException {
        return userService.getUsers();
    }

    @PostMapping(path = "/validateUser", consumes = "application/json", produces = "application/json")
    public ResponseEntity<User> validateUser(@RequestBody User user)
    {
        return new ResponseEntity<>(userService.validateUser(user), HttpStatus.OK);
    }

    @PostMapping(path = "/users", consumes = "application/json", produces = "application/json")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.addUser(user.username(), user.password()), HttpStatus.OK);
    }

}
