package model;

import model.excepciones.InvalidUsernameOrPasswordException;

public class User {

    private String username;
    private String password;

    public User(String username, String password){
        if(username.isEmpty() || password.isEmpty()){
            throw new InvalidUsernameOrPasswordException();
        }
        this.username = username;
        this.password = password;
    }

    public String username() { return this.username; }
}
