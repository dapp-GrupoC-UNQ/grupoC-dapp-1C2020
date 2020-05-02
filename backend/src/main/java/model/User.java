package model;

import com.example.demo.serializers.UserJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import model.excepciones.InvalidUsernameOrPasswordException;

@JsonSerialize(using = UserJsonSerializer.class)
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

    public String password() { return this.password; }

    public Boolean isAdminOfStore() { return false;}
}
