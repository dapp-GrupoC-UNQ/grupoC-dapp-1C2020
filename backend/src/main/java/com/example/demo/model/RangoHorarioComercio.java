package com.example.demo.model;

import com.example.demo.model.exceptions.HorarioNoPermitidoException;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class RangoHorarioComercio {

    DayOfWeek diaDeAtencion;
    LocalTime horaDeApertura;
    LocalTime horaDeCierre;

    public RangoHorarioComercio(DayOfWeek unDia, LocalTime horaInicio, LocalTime horaFin) {
        if(horaFin.isBefore(horaInicio)){ throw new HorarioNoPermitidoException(); }
        diaDeAtencion = unDia;
        horaDeApertura = horaInicio;
        horaDeCierre = horaFin;
    }

    public DayOfWeek dia() {
        return diaDeAtencion;
    }

    public boolean estaDisponibleEnHorario(DayOfWeek dia, LocalTime hora) {
        return diaDeAtencion.equals(dia)  && (horaDeApertura.isBefore(hora) || horaDeApertura.equals(hora))
                                          && (horaDeCierre.isAfter(hora) || horaDeCierre.equals(hora));
    }

    public LocalTime horaDeApertura() {
        return this.horaDeApertura;
    }
}
