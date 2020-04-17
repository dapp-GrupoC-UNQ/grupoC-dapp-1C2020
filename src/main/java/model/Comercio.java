package model;

import model.excepciones.ProductoInexistenteEnComercioException;
import model.excepciones.ProductoRepetidoEnComercioException;

import javax.swing.event.InternalFrameEvent;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BooleanSupplier;

public class Comercio {

    String nombreDeComercio;
    String rubroDeComercio;
    String domicilioDeComercio;
    Integer distanciaDeliveryEnKmComercio;
    List<String> mediosDePagoDisponiblesComercio;
    List<RangoHorarioComercio> horarioDeAtencionComercio;
    List<Producto> listaDeProductos = new ArrayList<>();

    public Comercio(String nombre, String rubro, String direccion, Integer distanciaDeliveryEnKm, List<String> mediosDePago, List<RangoHorarioComercio> horarioDeAtencion) {
         nombreDeComercio = nombre;
         rubroDeComercio = rubro;
         domicilioDeComercio = direccion;
         distanciaDeliveryEnKmComercio = distanciaDeliveryEnKm;
         mediosDePagoDisponiblesComercio =  mediosDePago;
         horarioDeAtencionComercio = horarioDeAtencion;
    }

    public String nombre() {
        return this.nombreDeComercio;
    }

    public String domicilio() {
        return this.domicilioDeComercio;
    }

    public Integer distanciaDeliveryEnKm() {
        return this.distanciaDeliveryEnKmComercio;
    }

    public String rubro() {
        return this.rubroDeComercio;
    }

    public Integer cantidadMediosDePago() { return mediosDePagoDisponiblesComercio.size();   }

    public Boolean estaDisponibleEn(DayOfWeek dia, LocalTime hora) {
        return   horarioDeAtencionComercio.stream().anyMatch(horario -> horario.estaDisponibleEnHorario(dia, hora));
    }

    public void agregarProducto(String nombre, String marca, Double precioDeVenta, Integer stockDisponible) {
        if(this.vendeProducto(nombre, marca)) { throw new ProductoRepetidoEnComercioException();}
        listaDeProductos.add(new Producto(nombre, marca, precioDeVenta, stockDisponible));
    }

    public Boolean vendeProducto(String nombreProducto, String marcaProducto) {
      return listaDeProductos.stream().anyMatch(producto -> this.sonElMismoProducto(producto, nombreProducto, marcaProducto));
    }

    private Boolean sonElMismoProducto(Producto unProducto, String unNombreProducto, String unaMarcaProducto) {
        return unProducto.nombre().equals(unNombreProducto) && unProducto.marca().equals(unaMarcaProducto);
    }

    public Boolean tieneProductos() {
        return !listaDeProductos.isEmpty();
    }

    private Producto encontrarProducto(String nombreProducto, String marcaProducto) {
        return listaDeProductos.stream()
                .filter(producto -> this.sonElMismoProducto(producto, nombreProducto, marcaProducto))
                .findFirst()
                .orElseThrow(ProductoInexistenteEnComercioException::new);
    }

    public Integer stockPara(String unNombre, String unaMarca) {
        return this.encontrarProducto(unNombre, unaMarca).stock();
    }

    public Double precioPara(String unNombre, String unaMarca) {
        return this.encontrarProducto(unNombre, unaMarca).precio();
    }

    public void actualizarPrecio(String nombreProducto, String marcaProducto, Double nuevoPrecio) {
        this.encontrarProducto(nombreProducto, marcaProducto).actualizarPrecio(nuevoPrecio);
    }

    public void agregarStock(String nombreProducto, String marcaProducto, Integer stockAAgregar) {
        this.encontrarProducto(nombreProducto, marcaProducto).agregarStock(stockAAgregar);
    }

    public void decrementarStock(String nombreProducto, String marcaProducto, Integer stockADecrementar) {
        this.encontrarProducto(nombreProducto, marcaProducto).decrementarStock(stockADecrementar);
    }
}
