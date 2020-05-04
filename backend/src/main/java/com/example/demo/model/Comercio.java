package com.example.demo.model;

import com.example.demo.model.merchandise.Merchandise;
import com.example.demo.serializers.StoreJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.example.demo.model.excepciones.NotFoundProductInStore;
import com.example.demo.model.excepciones.RepeatedMerchandiseInStore;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@JsonSerialize(using = StoreJsonSerializer.class)
public class Comercio {

    String nombreDeComercio;
    String rubroDeComercio;
    String domicilioDeComercio;
    Integer distanciaDeliveryEnKmComercio;
    List<String> mediosDePagoDisponiblesComercio;
    List<RangoHorarioComercio> horarioDeAtencionComercio;
    List<Discount> discountList = new ArrayList<>();
    List<Merchandise> merchandiseList = new ArrayList<>();

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

    public Boolean vendeProducto(String nombreProducto, String marcaProducto) {
      return merchandiseList.stream().anyMatch(mercaderia -> this.sonLaMismaMercaderia(mercaderia, nombreProducto, marcaProducto));
    }

    private Boolean sonLaMismaMercaderia(Merchandise mercaderia, String unNombreProducto, String unaMarcaProducto) {
        return mercaderia.name().equals(unNombreProducto) && mercaderia.brand().equals(unaMarcaProducto);
    }

    private Merchandise encontrarMercaderia(String nombreProducto, String marcaProducto) {
        return merchandiseList.stream()
                .filter(mercaderia -> this.sonLaMismaMercaderia(mercaderia, nombreProducto, marcaProducto))
                .findFirst()
                .orElseThrow(NotFoundProductInStore::new);
    }

    public Boolean sePuedeAbonarCon(String medioDePago) {
        return mediosDePagoDisponiblesComercio.contains(medioDePago);
    }

    public Boolean hasMerchandises() {
        return !merchandiseList.isEmpty();
    }

    public void addMerchandise(String name, String brand, Double price, Integer stock) {
        if(this.vendeProducto(name, brand)) { throw new RepeatedMerchandiseInStore();}
        merchandiseList.add(new Merchandise(name, brand, price, stock));
    }

    public Boolean sellsMerchandise(String name, String brand) {
        return merchandiseList.stream().anyMatch(merchandise -> this.sonLaMismaMercaderia(merchandise, name, brand));
    }

    public Integer stockOf(String name, String brand) {
        return this.encontrarMercaderia(name, brand).stock();
    }

    public Double priceOf(String name, String brand) {
        return this.encontrarMercaderia(name, brand).price();
    }

    public void updatePriceFor(String name, String brand, Double newPrice) {
        this.encontrarMercaderia(name, brand).updatePrice(newPrice);
    }

    public void addStock(String name, String brand, Integer newStock) {
        this.encontrarMercaderia(name, brand).addStock(newStock);
    }

    public void decreaseStock(String name, String brand, Integer stockToDecrese) {
        this.encontrarMercaderia(name, brand).decreaseStock(stockToDecrese);
    }

    public List<Merchandise> listOfAvailableMerchandise() {
        return this.merchandiseList.stream().filter(merchandise -> merchandise.stock() > 0).collect(Collectors.toList());
    }

    public void addDiscount(Discount discount) {
        this.discountList.add(discount);
    }

    public Boolean hasADiscount(Discount discount) {
        return this.discountList.contains(discount);
    }
}
