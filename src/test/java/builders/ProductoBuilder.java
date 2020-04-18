package builders;

import model.Producto;

public class ProductoBuilder {

    public static ProductoBuilder unProducto() {
        return new ProductoBuilder();
    }

    private String marcaDeProducto = "Matarazzo";
    private String nombreDeProducto = "Fideos tallarin";
    private Double precioDeProducto = 15.04;
    private Integer stockDeProducto = 24;

    public Producto build() {
        return new Producto(nombreDeProducto, marcaDeProducto, precioDeProducto, stockDeProducto);
    }

    public ProductoBuilder conNombre(String unNombre) {
        nombreDeProducto = unNombre;
        return this;
    }

    public ProductoBuilder conMarca(String unaMarca) {
        marcaDeProducto = unaMarca;
        return this;
    }

    public ProductoBuilder conStock(Integer unStock) {
        stockDeProducto = unStock;
        return this;
    }

    public ProductoBuilder conPrecio(Double unPrecio) {
        precioDeProducto = unPrecio;
        return this;
    }
}
