package com.example.demo.model.excepciones;

public class MercaderiaConStockNegativoException extends RuntimeException {
    public MercaderiaConStockNegativoException(){
        super("No se puede tener stock negativo de un producto");
    }

}
