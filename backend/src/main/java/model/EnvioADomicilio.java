package model;

import model.excepciones.OptionNotAvailableForThisDeliveryType;

import java.time.LocalDateTime;

public class EnvioADomicilio implements TipoDeEnvio {

    private String direccionDeEnvio;

    public EnvioADomicilio(String direccion){
        this.direccionDeEnvio = direccion;
    }
    @Override
    public String direccionDeEnvio() {
        return this.direccionDeEnvio;
    }

    @Override
    public LocalDateTime horarioDeRetiro() { throw new OptionNotAvailableForThisDeliveryType();  }
}
