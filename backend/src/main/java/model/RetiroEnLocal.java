package model;

import model.excepciones.OptionNotAvailableForThisDeliveryType;

import java.time.LocalDateTime;

public class RetiroEnLocal implements TipoDeEnvio{

    private LocalDateTime horaDeRetiro;

    public RetiroEnLocal(LocalDateTime hora){
        this.horaDeRetiro = hora;
    }

    @Override
    public String direccionDeEnvio() { throw new OptionNotAvailableForThisDeliveryType(); }

    @Override
    public LocalDateTime horarioDeRetiro() {
        return this.horaDeRetiro;
    }
}
