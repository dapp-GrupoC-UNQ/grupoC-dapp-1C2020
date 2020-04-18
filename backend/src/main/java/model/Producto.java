package model;

public class Producto {

    private String marcaDeProducto;
    private String nombreDeProducto;

    public Producto(String unNombre, String unaMarca) {
        nombreDeProducto = unNombre;
        marcaDeProducto = unaMarca;
    }

    public String nombre() {
        return this.nombreDeProducto;
    }

    public String marca() {
        return this.marcaDeProducto;
    }

   /* public Integer stock() {
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
    }*/
}
