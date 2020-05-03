package com.example.demo.model.excepciones;

public class NotFoundStoreException extends RuntimeException{
    public NotFoundStoreException() { super("Store was not found");}
}
