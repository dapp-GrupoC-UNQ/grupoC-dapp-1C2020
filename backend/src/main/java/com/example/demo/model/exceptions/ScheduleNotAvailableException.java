package com.example.demo.model.exceptions;

public class ScheduleNotAvailableException extends RuntimeException {

    public ScheduleNotAvailableException(){
        super("El horario de cierre no puede ser anterior al horario de apertura");
    }
}
