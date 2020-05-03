package com.example.demo.model.excepciones;

public class HorarioNoPermitidoException extends RuntimeException {

    public HorarioNoPermitidoException(){
        super("El horario de cierre no puede ser anterior al horario de apertura");
    }
}
