package com.example.demo.model.excepciones;

public class MercaderiaConPrecioNegativoException extends RuntimeException {
    public MercaderiaConPrecioNegativoException(){
        super("No se puede tener un producto con precio negativo");
    }
}
