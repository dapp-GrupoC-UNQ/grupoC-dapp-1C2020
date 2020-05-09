package com.example.demo.builders;

import com.example.demo.model.Store;
import com.example.demo.model.RangoHorarioComercio;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ComercioBuilder {
    public static ComercioBuilder unComercio() {
        return new ComercioBuilder();
    }

    public static List<Store> storeList() {
        Store store = unComercio().build();
        Store anotherStore = unComercio().conNombre("Coto").conRubro("Almacen").build();
        return Arrays.asList(store, anotherStore);
    }
    private String nombreDeComercio = "Jumbo";
    private String rubroDeComercio = "Supermercado";
    private String domicilioDeComercio = "Calchaqui 123";
    private Integer distanciaMaximaDePedidosEnKm = 3;
    private List<String> mediosDePagoDisponibles = Arrays.asList("Efectivo");
    private List<RangoHorarioComercio> horarioDeAtencionComercio = Arrays.asList(new RangoHorarioComercio(DayOfWeek.FRIDAY, LocalTime.of(9,0), LocalTime.of(15, 0)));

    public static List<Store> storeWithACategoryList(String category) {
        return storeList().stream().filter(store -> store.storeCategory().equals(category)).collect(Collectors.toList());
    }

    public Store build() {
        return new Store(nombreDeComercio, rubroDeComercio, domicilioDeComercio, distanciaMaximaDePedidosEnKm, mediosDePagoDisponibles, horarioDeAtencionComercio);
    }

    public ComercioBuilder conNombre(String unNombre) {
        nombreDeComercio = unNombre;
        return this;
    }

    public ComercioBuilder conDomicilio(String unaDireccion) {
        domicilioDeComercio = unaDireccion;
        return this;
    }

    public ComercioBuilder conDistanciaDeliveryEnKm(Integer distanciaEnKm) {
        distanciaMaximaDePedidosEnKm = distanciaEnKm;
        return this;
    }

    public ComercioBuilder conRubro(String unRubro) {
        rubroDeComercio = unRubro;
        return this;
    }

    public ComercioBuilder conMediosDePago(List<String> mediosDePago){
        mediosDePagoDisponibles = mediosDePago;
        return this;
    }

    public ComercioBuilder conHorarioDeAtencion(List<RangoHorarioComercio> horarioComercio) {
        horarioDeAtencionComercio = horarioComercio;
        return this;
    }
}
