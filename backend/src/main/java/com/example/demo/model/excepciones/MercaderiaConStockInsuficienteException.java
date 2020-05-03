package com.example.demo.model.excepciones;

public class MercaderiaConStockInsuficienteException extends RuntimeException {
    public MercaderiaConStockInsuficienteException(){
        super("La cantidad de stock es insuficiente");
    }
}
