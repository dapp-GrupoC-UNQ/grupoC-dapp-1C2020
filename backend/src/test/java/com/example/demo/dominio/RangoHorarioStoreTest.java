package com.example.demo.dominio;

import com.example.demo.model.RangoHorarioComercio;
import com.example.demo.model.excepciones.HorarioNoPermitidoException;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class RangoHorarioStoreTest {

    @Test
    public void unRangoHorarioDeComercioTieneUnDiaDeAtencion(){
        RangoHorarioComercio horarioComercio = new RangoHorarioComercio(DayOfWeek.FRIDAY, LocalTime.of(9, 0), LocalTime.of(19, 0));
        assertEquals(horarioComercio.dia(), DayOfWeek.FRIDAY);
    }

    @Test
    public void unRangoHorarioDeComercioEstaDisponibleEnUnDiaYHorario(){
        RangoHorarioComercio horarioComercio = new RangoHorarioComercio(DayOfWeek.FRIDAY, LocalTime.of(9, 0), LocalTime.of(19, 0));
        assertTrue(horarioComercio.estaDisponibleEnHorario(DayOfWeek.FRIDAY, LocalTime.of(11,0)));
    }

    @Test
    public void unRangoHorarioDeComercioNoPuedeTenerUnHorarioDeAperturaPosteriorAlHorarioDeCierre() {
        assertThrows(HorarioNoPermitidoException.class, () -> {    new RangoHorarioComercio(DayOfWeek.FRIDAY, LocalTime.of(19, 0), LocalTime.of(9, 0));     });
    }
}
