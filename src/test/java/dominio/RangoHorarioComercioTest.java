package dominio;

import model.RangoHorarioComercio;
import model.excepciones.HorarioNoPermitidoException;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class RangoHorarioComercioTest {

    @Test
    public void unRangoHorarioDeComercioTieneUnDiaDeAtencion(){
        try {
            RangoHorarioComercio horarioComercio = new RangoHorarioComercio(DayOfWeek.FRIDAY, LocalTime.of(9, 0), LocalTime.of(19, 0));
            assertEquals(horarioComercio.dia(), DayOfWeek.FRIDAY);
        } catch(Exception e){
            System.out.println("El horario de cierre no puede ser anterior al horario de apertura");
        }

    }

    @Test
    public void unRangoHorarioDeComercioEstaDisponibleEnUnDiaYHorario(){
        try{
            RangoHorarioComercio horarioComercio = new RangoHorarioComercio(DayOfWeek.FRIDAY, LocalTime.of(9, 0), LocalTime.of(19, 0));
            assertTrue(horarioComercio.estaDisponibleEnHorario(DayOfWeek.FRIDAY, LocalTime.of(11,0)));
        }catch(Exception e){
            System.out.println("El horario de cierre no puede ser anterior al horario de apertura");
        }

    }

    @Test
    public void unRangoHorarioDeComercioNoPuedeTenerUnHorarioDeAperturaPosteriorAlHorarioDeCierre() {

        assertThrows(HorarioNoPermitidoException.class, () -> {    new RangoHorarioComercio(DayOfWeek.FRIDAY, LocalTime.of(19, 0), LocalTime.of(9, 0));     });
    }
}
