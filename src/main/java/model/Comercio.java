package model;

import javax.swing.event.InternalFrameEvent;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class Comercio {

    String nombreDeComercio;
    String rubroDeComercio;
    String domicilioDeComercio;
    Integer distanciaDeliveryEnKmComercio;
    List<String> mediosDePagoDisponiblesComercio;
    List<RangoHorarioComercio> horarioDeAtencionComercio;

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
}
