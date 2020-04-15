package builders;

import model.Comercio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ComercioBuilder {
    public static ComercioBuilder unComercio() {
        return new ComercioBuilder();
    }

    private String nombreDeComercio = "Jumbo";
    private String rubroDeComercio = "Supermercado";
    private String domicilioDeComercio = "Calchaqui 123";
    private Integer distanciaMaximaDePedidosEnKm = 3;
    private List<String> mediosDePagoDisponibles = Arrays.asList("Efectivo");


    public Comercio build() {
        return new Comercio(nombreDeComercio, rubroDeComercio, domicilioDeComercio, distanciaMaximaDePedidosEnKm, mediosDePagoDisponibles);
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
}
