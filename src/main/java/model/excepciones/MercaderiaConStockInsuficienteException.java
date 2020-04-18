package model.excepciones;

public class MercaderiaConStockInsuficienteException extends RuntimeException {
    public MercaderiaConStockInsuficienteException(){
        super("La cantidad de stock es insuficiente");
    }
}
