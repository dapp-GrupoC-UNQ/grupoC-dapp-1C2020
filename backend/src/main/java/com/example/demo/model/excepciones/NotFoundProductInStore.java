package com.example.demo.model.excepciones;

public class NotFoundProductInStore extends RuntimeException {
    public NotFoundProductInStore(){
        super("This store does not sell that product");
    }
}
