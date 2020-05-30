package com.example.demo.model.user;

import com.example.demo.model.exceptions.InvalidUsernameOrPasswordException;

public abstract class User {

    String username;
    String password;

    public User(String username, String password) {
        if(username.isEmpty() || password.isEmpty()){
            throw new InvalidUsernameOrPasswordException();
        }
        this.username = username;
        this.password = password;
    }

    public User(){};

    public String username() { return this.username; }

    public String password() { return this.password; }

    public abstract Boolean isAdminOfStore();
}
