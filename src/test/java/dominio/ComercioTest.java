package dominio;

import builders.ComercioBuilder;
import model.Comercio;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

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

    @Test
    public void unComercioTieneUnMedioDePago() {
        List<String> mediosDePago = Arrays.asList("Efectivo");
        Comercio comercio = ComercioBuilder.unComercio().conMediosDePago(mediosDePago).build();
        assertEquals(comercio.cantidadMediosDePago(),  1);
    }

    @Test
    public void unComercioNoPuedeAtenderEnUnHorarioFueraDeSuRango() {
        /*RangoHorarioComercio horarioComercio = new RangoHorarioComercio(dia, horarioInicio, horarioFin);
        LocalDateTime horario = LocalDateTime.of(2020, 05, 05, 14, 00,);
        Comercio comercio = ComercioBuilder.unComercio().conHorarioDeAtencion(horarioComercio).build();
        assertTrue(comercio.estaDisponibleEn(horario));*/
    }

}
