package model;

import model.excepciones.HorarioNoPermitidoException;
import model.excepciones.ProductoConPrecioNegativoException;
import model.excepciones.ProductoConStockNegativoException;

public class Producto {

    private Comercio comercioDelProducto;
    private String marcaDeProducto;
    private String nombreDeProducto;
    private Double precioDeProducto;
    private Integer stockDeProducto;

    public Producto(Comercio unComercio, String unNombre, String unaMarca, Double unPrecio, Integer unStock) {
        if(unStock < 0){ throw new ProductoConStockNegativoException(); }
        if(unPrecio < 0){ throw new ProductoConPrecioNegativoException(); }
        comercioDelProducto = unComercio;
        nombreDeProducto = unNombre;
        marcaDeProducto = unaMarca;
        stockDeProducto = unStock;
        precioDeProducto = unPrecio;
    }

    public Comercio comercio() {
        return this.comercioDelProducto;
    }

    public String nombre() {
        return this.nombreDeProducto;
    }

    public String marca() {
        return this.marcaDeProducto;
    }

    public Integer stock() {
        return this.stockDeProducto;
    }

    public Double precio() {
        return this.precioDeProducto;
    }
}
