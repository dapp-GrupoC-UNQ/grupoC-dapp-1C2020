package dominio;

import builders.ComercioBuilder;
import model.Comercio;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComercioTest {

    @Test
    public void unComercioTieneUnNombre() {
        Comercio comercio = ComercioBuilder.unComercio().conNombre("Disco").build();
        assertEquals(comercio.nombre(), "Disco");
    }

    @Test
    public void unComercioTieneUnaDireccion() {
        Comercio comercio = ComercioBuilder.unComercio().conDomicilio("Calchaqui 423").build();
        assertEquals(comercio.domicilio(), "Calchaqui 423");
    }

    @Test
    public void unComercioTieneUnRubro() {
        //podrian ser varios?
        Comercio comercio = ComercioBuilder.unComercio().conRubro("Supermercado").build();
        assertEquals(comercio.rubro(),  "Supermercado");
    }

    @Test
    public void unComercioTieneUnaDistanciaMaximaDeDelivery() {
        Comercio comercio = ComercioBuilder.unComercio().conDistanciaDeliveryEnKm(4).build();
        assertEquals(comercio.distanciaDeliveryEnKm(),  4);
    }
}
