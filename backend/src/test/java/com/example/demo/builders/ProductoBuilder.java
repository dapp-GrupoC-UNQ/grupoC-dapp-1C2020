package com.example.demo.builders;

import com.example.demo.model.Producto;

public class ProductoBuilder {

    public static ProductoBuilder unProducto() {
        return new ProductoBuilder();
    }

    private String marcaDeProducto = "Matarazzo";
    private String nombreDeProducto = "Fideos tallarin";

    public Producto build() {
        return new Producto(nombreDeProducto, marcaDeProducto);
    }

    public ProductoBuilder conNombre(String unNombre) {
        nombreDeProducto = unNombre;
        return this;
    }

    public ProductoBuilder conMarca(String unaMarca) {
        marcaDeProducto = unaMarca;
        return this;
    }
}
