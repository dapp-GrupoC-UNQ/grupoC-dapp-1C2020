package model;

import model.excepciones.HorarioNoPermitidoException;
import model.excepciones.ProductoConPrecioNegativoException;
import model.excepciones.ProductoConStockNegativoException;

public class Producto {

    private String marcaDeProducto;
    private String nombreDeProducto;
    private Double precioDeProducto;
    private Integer stockDeProducto;

    public Producto(String unNombre, String unaMarca, Double unPrecio, Integer unStock) {
        if(unStock < 0){ throw new ProductoConStockNegativoException(); }
        if(unPrecio < 0){ throw new ProductoConPrecioNegativoException(); }
        nombreDeProducto = unNombre;
        marcaDeProducto = unaMarca;
        stockDeProducto = unStock;
        precioDeProducto = unPrecio;
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

    public void actualizarPrecio(Double nuevoPrecio) {
        precioDeProducto = nuevoPrecio;
    }

    public void agregarStock(Integer stockAAgregar) {
        stockDeProducto += stockAAgregar;
    }

    public void decrementarStock(Integer stockADecrementar) {
        stockDeProducto -= stockADecrementar;
    }
}
