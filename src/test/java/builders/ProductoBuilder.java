package builders;

import model.Comercio;
import model.Producto;

public class ProductoBuilder {

    public static ProductoBuilder unProducto() {
        return new ProductoBuilder();
    }

    private Comercio comercioDelProducto = ComercioBuilder.unComercio().build();
    private String marcaDeProducto = "Matarazzo";
    private String nombreDeProducto = "Fideos tallarin";
    private Double precioDeProducto = 15.04;
    private Integer stockDeProducto = 24;

    public Producto build() {
        return new Producto(comercioDelProducto, nombreDeProducto, marcaDeProducto, precioDeProducto, stockDeProducto);
    }

    public ProductoBuilder deComercio(Comercio unComercio) {
        comercioDelProducto = unComercio;
        return this;
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
