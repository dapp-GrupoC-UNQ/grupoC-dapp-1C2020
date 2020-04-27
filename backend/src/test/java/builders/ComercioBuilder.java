package builders;

import model.Comercio;
import model.RangoHorarioComercio;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ComercioBuilder {
    public static ComercioBuilder unComercio() {
        return new ComercioBuilder();
    }

    public static List<Comercio> storeList() {
        Comercio store = unComercio().build();
        Comercio anotherStore = unComercio().conNombre("Coto").build();
        return Arrays.asList(store, anotherStore);
    }
    private String nombreDeComercio = "Jumbo";
    private String rubroDeComercio = "Supermercado";
    private String domicilioDeComercio = "Calchaqui 123";
    private Integer distanciaMaximaDePedidosEnKm = 3;
    private List<String> mediosDePagoDisponibles = Arrays.asList("Efectivo");
    private List<RangoHorarioComercio> horarioDeAtencionComercio = Arrays.asList(new RangoHorarioComercio(DayOfWeek.FRIDAY, LocalTime.of(9,0), LocalTime.of(15, 0)));

    public Comercio build() {
        return new Comercio(nombreDeComercio, rubroDeComercio, domicilioDeComercio, distanciaMaximaDePedidosEnKm, mediosDePagoDisponibles, horarioDeAtencionComercio);
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
