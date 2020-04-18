package model.excepciones;

public class ProductoInexistenteEnComercioException extends RuntimeException {
    public ProductoInexistenteEnComercioException(){
        super("Este comercio no vende ese producto");
    }
}
