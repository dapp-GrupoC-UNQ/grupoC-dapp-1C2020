package com.example.demo.model.excepciones;

public class InvalidStockTypeException extends RuntimeException {

    public InvalidStockTypeException() {
        super("Impossible to add a negative amount of products to merchandise stock");
    }
}
