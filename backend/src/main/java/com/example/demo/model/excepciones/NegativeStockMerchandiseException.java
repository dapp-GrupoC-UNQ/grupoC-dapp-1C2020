package com.example.demo.model.excepciones;

public class NegativeStockMerchandiseException extends RuntimeException {
    public NegativeStockMerchandiseException(){
        super("No se puede tener stock negativo de un producto");
    }

}
