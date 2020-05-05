package com.example.demo.model.excepciones;

public class NotAvailableUserNameException extends RuntimeException{
    public NotAvailableUserNameException() {
        super("This username is not available");
    }
}
