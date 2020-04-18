package builders;

import model.Mercaderia;

public class MercaderiaBuilder {

    private String nombreProducto = "Fideos";
    private String marcaProducto = "Matarazzo";
    private Integer stockProducto = 9;
    private Double precioProducto = 65.0;

    public static MercaderiaBuilder unaMercaderia() {
        return new MercaderiaBuilder();
    }

    public Mercaderia build() {
        return new Mercaderia(nombreProducto, marcaProducto, precioProducto, stockProducto);
    }


    public MercaderiaBuilder conProducto(String nombre, String marca) {
        nombreProducto = nombre;
        marcaProducto = marca;
        return this;
    }

    public MercaderiaBuilder conStock(Integer stock) {
        stockProducto = stock;
        return this;
    }

    public MercaderiaBuilder conPrecio(Double unPrecio) {
        precioProducto = unPrecio;
        return this;
    }
}
