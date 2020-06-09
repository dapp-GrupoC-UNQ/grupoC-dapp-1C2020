package com.example.demo.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ValidationUserDTO {
    @JsonProperty
    private String username;
    @JsonProperty
    private String password;

    @JsonCreator
    public ValidationUserDTO(String email, String password)
    {
        this.username = email;
        this.password = password;
    }

    public ValidationUserDTO(){};

    public String getUsername() {
        return this.username;
    }
    public String getPassword() { return this.password; }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
