package model;

import java.time.LocalDateTime;

public class RetiroEnLocal implements TipoDeEnvio{

    private LocalDateTime horaDeRetiro;

    public RetiroEnLocal(LocalDateTime hora){
        this.horaDeRetiro = hora;
    }

    @Override
    public String direccionDeEnvio() {
        return null;
    }

    @Override
    public LocalDateTime horarioDeRetiro() {
        return this.horaDeRetiro;
    }
}
