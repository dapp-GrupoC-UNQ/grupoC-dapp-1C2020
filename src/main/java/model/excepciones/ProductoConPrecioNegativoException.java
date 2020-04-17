package model.excepciones;

public class ProductoConPrecioNegativoException extends RuntimeException {
    public ProductoConPrecioNegativoException(){
        super("No se puede tener un producto con precio negativo");
    }
}
