package com.example.demo.model.user;

import com.example.demo.deserializers.UserJsonDeserializer;
import com.example.demo.model.exceptions.InvalidUsernameOrPasswordException;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="user_type",
        discriminatorType = DiscriminatorType.STRING)
@JsonDeserialize(using = UserJsonDeserializer.class)
public abstract class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String username;
    private String password;

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

    public void setUsername(String username) {
        this.username = username;
    };

    public void setPassword(String password) {
        this.password = password;
    }

    public Long id() {
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }
}
