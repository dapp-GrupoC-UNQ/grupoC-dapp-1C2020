package com.example.demo.model.exceptions;

public class NoProductsAvailableInStore extends RuntimeException {
    public NoProductsAvailableInStore(){ super("No products available in selected store"); }
}
