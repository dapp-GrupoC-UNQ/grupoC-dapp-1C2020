package com.example.demo.model.exceptions;

public class RepeatedMerchandiseInStore extends RuntimeException {
    public RepeatedMerchandiseInStore(){
        super("No se puede agregar dos veces el mismo producto para un comercio");
    }
}
