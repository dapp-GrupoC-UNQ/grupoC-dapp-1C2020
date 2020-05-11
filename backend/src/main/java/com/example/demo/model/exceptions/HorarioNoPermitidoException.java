package com.example.demo.model.exceptions;

public class HorarioNoPermitidoException extends RuntimeException {

    public HorarioNoPermitidoException(){
        super("El horario de cierre no puede ser anterior al horario de apertura");
    }
}
