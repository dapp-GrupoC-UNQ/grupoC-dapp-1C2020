package com.example.demo.model;

import com.example.demo.model.excepciones.MercaderiaConPrecioNegativoException;
import com.example.demo.model.excepciones.MercaderiaConStockInsuficienteException;
import com.example.demo.model.excepciones.MercaderiaConStockNegativoException;

public class Mercaderia {

    private Producto producto;
    private Double precioDeProducto;
    private Integer stockDeProducto;

    public Mercaderia(String nombreProducto, String marcaProducto, Double precioDeVenta, Integer stockDisponible) {
        if(stockDisponible < 0) { throw new MercaderiaConStockNegativoException();}
        if(precioDeVenta < 0) { throw new MercaderiaConPrecioNegativoException();}
        producto = new Producto(nombreProducto, marcaProducto);
        stockDeProducto = stockDisponible;
        precioDeProducto = precioDeVenta;
    }

    public Producto producto() {
        return this.producto;
    }

    public Integer stock() {
        return this.stockDeProducto;
    }

    public Double precio() {
        return this.precioDeProducto;
    }

    public void actualizarPrecio(Double nuevoPrecio) {
        if(nuevoPrecio < 0) { throw new MercaderiaConPrecioNegativoException();}
        precioDeProducto = nuevoPrecio;
    }

    public void agregarStock(Integer stockAAgregar) {
        stockDeProducto += stockAAgregar;
    }

    public void decrementarStock(Integer stockADecrementar) {
        if(stockDeProducto - stockADecrementar < 0) { throw new MercaderiaConStockInsuficienteException();}
        stockDeProducto -= stockADecrementar;
    }
}
