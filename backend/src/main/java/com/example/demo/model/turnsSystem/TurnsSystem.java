package com.example.demo.model.turnsSystem;

import com.example.demo.model.RangoHorarioComercio;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TurnsSystem {

    public static LocalDateTime primerTurnoDeLocal(LocalDate openingTime, List<RangoHorarioComercio> storeSchedule) {
        List<RangoHorarioComercio> storeTime = storeSchedule;
        List<LocalDate> diaDeApertura = storeTime.stream().map(time -> proximaFechaConDia(time.dia(), openingTime)).collect(Collectors.toList());
        LocalDate diaMasCercanoANow = diaDeApertura.stream().min(Comparator.comparingLong(x -> ChronoUnit.DAYS.between(openingTime, x))).get();
        return encontrarHorarioApertura(diaMasCercanoANow, storeSchedule);
    }

    private static LocalDateTime encontrarHorarioApertura(LocalDate day, List<RangoHorarioComercio> storeSchedule) {
        List<RangoHorarioComercio> storeTime = storeSchedule;
        storeTime = storeTime.stream().filter(schedule -> schedule.dia().equals(day.getDayOfWeek())).collect(Collectors.toList());
        RangoHorarioComercio horario = storeTime.stream().min(Comparator.comparing(RangoHorarioComercio::horaDeApertura)).get();
        return LocalDateTime.of(day.getYear(),day.getMonth(), day.getDayOfMonth(), horario.horaDeApertura().getHour(), horario.horaDeApertura().getMinute());
    }


    private static LocalDate proximaFechaConDia(DayOfWeek dayOfWeek, LocalDate aPartirDe) {
        LocalDate now = aPartirDe;
        return now.with(TemporalAdjusters.next(dayOfWeek));
    }
}
