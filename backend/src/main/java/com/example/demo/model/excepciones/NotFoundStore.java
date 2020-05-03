package com.example.demo.model.excepciones;

public class NotFoundStore extends RuntimeException{
    public NotFoundStore() { super("Store was not found");}
}
