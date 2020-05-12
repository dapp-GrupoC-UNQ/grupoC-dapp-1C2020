package com.example.demo.model.store;

import com.example.demo.builders.StoreBuilder;
import com.example.demo.model.RangoHorarioComercio;
import com.example.demo.model.Store;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StoreTurnsSystemTest {

    @Test
    public void elProximoTurnoDeUnNuevoComercioSeraElPrimerHorarioDisponiblePostAperturaQueEsteEnSuRangoHorario(){
        RangoHorarioComercio rangoMiercoles = new RangoHorarioComercio(DayOfWeek.WEDNESDAY, LocalTime.of(10,0), LocalTime.of(12,30));
        RangoHorarioComercio rangoJueves = new RangoHorarioComercio(DayOfWeek.THURSDAY, LocalTime.of(9,0), LocalTime.of(16,15));
        LocalDateTime askingForTurnMoment = LocalDateTime.of(2020, 5, 6, 14, 5);
        LocalDate openingDate = askingForTurnMoment.toLocalDate();
        Store store = StoreBuilder.aStore().withPaymentMethods(Arrays.asList(rangoMiercoles, rangoJueves)).withOpeningDate(openingDate).build();
        assertEquals(store.nextTurn(askingForTurnMoment), LocalDateTime.of(2020, 5, 7, 9,0));
    }

    @Test
    public void siPidoDosTurnosYHayDosDisponiblesEnElMismoDiaSeranOtorgadosParaElMismoDiaCon15MinDeDiferencia() {
        RangoHorarioComercio rangoMiercoles = new RangoHorarioComercio(DayOfWeek.WEDNESDAY, LocalTime.of(10,0), LocalTime.of(12,30));
        LocalDateTime askingForTurnMoment = LocalDateTime.of(2020, 5, 6, 14, 5);
        LocalDate openingDate = askingForTurnMoment.toLocalDate();
        Store store = StoreBuilder.aStore().withPaymentMethods(Arrays.asList(rangoMiercoles)).withOpeningDate(openingDate).build();
        LocalDateTime firstGivenTurn = store.nextTurn(askingForTurnMoment);
        LocalDateTime secondGivenTurn = store.nextTurn(askingForTurnMoment);
        assertEquals(secondGivenTurn, firstGivenTurn.plusMinutes(15));
    }

    @Test
    public void siPidoDosTurnosYHayUnoSoloDisponibleParaEseDiaElSegundoTurnoTendraUnDiaDistinto() {
        RangoHorarioComercio rangoMiercolesCorto = new RangoHorarioComercio(DayOfWeek.WEDNESDAY, LocalTime.of(10,0), LocalTime.of(10, 15));
        LocalDateTime askingForTurnMoment = LocalDateTime.of(2020, 5, 6, 14, 5);
        LocalDate openingDate = askingForTurnMoment.toLocalDate();
        Store store = StoreBuilder.aStore().withPaymentMethods(Arrays.asList(rangoMiercolesCorto)).withOpeningDate(openingDate).build();
        LocalDateTime firstGivenTurn = store.nextTurn(askingForTurnMoment);
        LocalDateTime secondGivenTurn = store.nextTurn(askingForTurnMoment);
        LocalDateTime thirdturn = store.nextTurn(askingForTurnMoment);
        assertEquals(secondGivenTurn, firstGivenTurn.plusMinutes(15));
    }
}
