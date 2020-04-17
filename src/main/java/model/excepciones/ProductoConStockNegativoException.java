package model.excepciones;

public class ProductoConStockNegativoException extends RuntimeException {
    public ProductoConStockNegativoException(){
        super("No se puede tener stock negativo de un producto");
    }

}
