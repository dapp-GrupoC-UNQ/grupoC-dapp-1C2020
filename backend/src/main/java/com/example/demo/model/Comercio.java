package com.example.demo.model;

import com.example.demo.serializers.StoreJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.example.demo.model.excepciones.ProductoInexistenteEnComercioException;
import com.example.demo.model.excepciones.ProductoRepetidoEnComercioException;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@JsonSerialize(using = StoreJsonSerializer.class)
public class Comercio {

    String nombreDeComercio;
    String rubroDeComercio;
    String domicilioDeComercio;
    Integer distanciaDeliveryEnKmComercio;
    List<String> mediosDePagoDisponiblesComercio;
    List<RangoHorarioComercio> horarioDeAtencionComercio;
    List<Mercaderia> listaDeMercaderias = new ArrayList<>();

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

    public List<String> mediosDePagoComercio() { return this.mediosDePagoDisponiblesComercio; }

    public Integer cantidadMediosDePago() { return mediosDePagoDisponiblesComercio.size();   }

    public Boolean estaDisponibleEn(DayOfWeek dia, LocalTime hora) {
        return   horarioDeAtencionComercio.stream().anyMatch(horario -> horario.estaDisponibleEnHorario(dia, hora));
    }

    public void agregarMercaderia(String nombre, String marca, Double precioDeVenta, Integer stockDisponible) {
        if(this.vendeProducto(nombre, marca)) { throw new ProductoRepetidoEnComercioException();}
        listaDeMercaderias.add(new Mercaderia(nombre, marca, precioDeVenta, stockDisponible));
    }

    public Boolean vendeProducto(String nombreProducto, String marcaProducto) {
      return listaDeMercaderias.stream().anyMatch(mercaderia -> this.sonLaMismaMercaderia(mercaderia, nombreProducto, marcaProducto));
    }

    private Boolean sonLaMismaMercaderia(Mercaderia mercaderia, String unNombreProducto, String unaMarcaProducto) {
        return mercaderia.producto().nombre().equals(unNombreProducto) && mercaderia.producto().marca().equals(unaMarcaProducto);
    }

    public Boolean tieneProductos() {
        return !listaDeMercaderias.isEmpty();
    }

    private Mercaderia encontrarMercaderia(String nombreProducto, String marcaProducto) {
        return listaDeMercaderias.stream()
                .filter(mercaderia -> this.sonLaMismaMercaderia(mercaderia, nombreProducto, marcaProducto))
                .findFirst()
                .orElseThrow(ProductoInexistenteEnComercioException::new);
    }

    public Integer stockPara(String unNombre, String unaMarca) {
        return this.encontrarMercaderia(unNombre, unaMarca).stock();
    }

    public Double precioPara(String unNombre, String unaMarca) {
        return this.encontrarMercaderia(unNombre, unaMarca).precio();
    }

    public void actualizarPrecio(String nombreProducto, String marcaProducto, Double nuevoPrecio) {
        this.encontrarMercaderia(nombreProducto, marcaProducto).actualizarPrecio(nuevoPrecio);
    }

    public void agregarStock(String nombreProducto, String marcaProducto, Integer stockAAgregar) {
        this.encontrarMercaderia(nombreProducto, marcaProducto).agregarStock(stockAAgregar);
    }

    public void decrementarStock(String nombreProducto, String marcaProducto, Integer stockADecrementar) {
        this.encontrarMercaderia(nombreProducto, marcaProducto).decrementarStock(stockADecrementar);
    }

    public Boolean sePuedeAbonarCon(String medioDePago) {
        return mediosDePagoDisponiblesComercio.contains(medioDePago);
    }
}
