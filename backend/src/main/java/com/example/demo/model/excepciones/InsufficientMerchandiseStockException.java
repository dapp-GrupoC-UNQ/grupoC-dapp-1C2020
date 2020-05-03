package com.example.demo.model.excepciones;

public class InsufficientMerchandiseStockException extends RuntimeException {
    public InsufficientMerchandiseStockException(){
        super("La cantidad de stock es insuficiente");
    }
}
