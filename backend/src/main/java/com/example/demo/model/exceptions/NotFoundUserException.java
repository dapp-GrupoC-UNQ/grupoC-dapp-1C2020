package com.example.demo.model.exceptions;

public class NotFoundUserException  extends RuntimeException {
    public NotFoundUserException(){
        super("Invalid username or password");
    }
}

