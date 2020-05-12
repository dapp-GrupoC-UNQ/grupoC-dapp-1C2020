package com.example.demo.model.store;

import com.example.demo.model.StoreSchedule;
import com.example.demo.model.exceptions.ScheduleNotAvailableException;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class StoreScheduleTest {

    @Test
    public void unRangoHorarioDeComercioTieneUnDiaDeAtencion(){
        StoreSchedule schedule = new StoreSchedule(DayOfWeek.FRIDAY, LocalTime.of(9, 0), LocalTime.of(19, 0));
        assertEquals(schedule.getDay(), DayOfWeek.FRIDAY);
    }

    @Test
    public void unRangoHorarioDeComercioEstaDisponibleEnUnDiaYHorario(){
        StoreSchedule schedule = new StoreSchedule(DayOfWeek.FRIDAY, LocalTime.of(9, 0), LocalTime.of(19, 0));
        assertTrue(schedule.isAvailableOnTime(DayOfWeek.FRIDAY, LocalTime.of(11,0)));
    }

    @Test
    public void unRangoHorarioDeComercioNoPuedeTenerUnHorarioDeAperturaPosteriorAlHorarioDeCierre() {
        assertThrows(ScheduleNotAvailableException.class, () -> {    new StoreSchedule(DayOfWeek.FRIDAY, LocalTime.of(19, 0), LocalTime.of(9, 0));     });
    }
}
