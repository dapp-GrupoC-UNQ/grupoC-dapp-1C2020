package com.example.demo.controllers;
import com.example.demo.model.user.User;
import com.example.demo.services.users.IUserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.example.demo.model.user.ClientUser;
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
    public ResponseEntity<User> validateUser(@RequestBody ClientUser clientUser)
    {
        return new ResponseEntity<>(userService.validateUser(clientUser), HttpStatus.OK);
    }

    @PostMapping(path = "/users", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ClientUser> createUser(@RequestBody ClientUser clientUser) {
        return new ResponseEntity<>(userService.addUser(clientUser.username(), clientUser.password()), HttpStatus.OK);
    }

}
