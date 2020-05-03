package com.example.demo.model.excepciones;

public class ProductoRepetidoEnComercioException extends RuntimeException {
    public ProductoRepetidoEnComercioException(){
        super("No se puede agregar dos veces el mismo producto para un comercio");
    }
}
