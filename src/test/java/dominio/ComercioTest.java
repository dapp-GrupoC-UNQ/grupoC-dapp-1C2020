package dominio;

import builders.ComercioBuilder;
import model.Comercio;
import model.RangoHorarioComercio;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


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
        try{
            List<RangoHorarioComercio> horarioComercio = Arrays.asList(new RangoHorarioComercio(DayOfWeek.FRIDAY, LocalTime.of(9,0), LocalTime.of(13, 0)));
            Comercio comercio = ComercioBuilder.unComercio().conHorarioDeAtencion(horarioComercio).build();
            assertFalse(comercio.estaDisponibleEn(DayOfWeek.FRIDAY, LocalTime.of(20,0)));
        }catch(Exception e){
            System.out.println("El horario de cierre no puede ser anterior al horario de apertura");
        }

    }

}
