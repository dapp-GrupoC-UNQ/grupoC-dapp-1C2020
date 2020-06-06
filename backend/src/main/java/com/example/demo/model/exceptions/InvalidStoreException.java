package com.example.demo.model.exceptions;


public class InvalidStoreException extends RuntimeException {
    public InvalidStoreException(){ super("Store is invalid, please check that all fields are complete."); }
}
