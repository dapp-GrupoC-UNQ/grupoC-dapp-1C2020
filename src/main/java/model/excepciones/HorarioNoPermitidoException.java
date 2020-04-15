package model.excepciones;

public class HorarioNoPermitidoException extends Exception {

    public HorarioNoPermitidoException(){
        super("El horario de cierre no puede ser anterior al horario de apertura");
    }
}
