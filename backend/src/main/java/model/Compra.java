package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Compra {

    private String medioDePago;
    private TipoDeEnvio tipoDeEnvio;
    private String direccionEnvio;
    private List<Mercaderia> listaDeMercaderia = new ArrayList<>();

    public Compra(String pago, TipoDeEnvio envio){
        medioDePago = pago;
        tipoDeEnvio = envio;
    }

    public String medioDePago(){
        return this.medioDePago;
    }

    public Double montoTotal() {
            return this.listaDeMercaderia.stream().mapToDouble(Mercaderia::precio).sum();
    }

    public TipoDeEnvio tipoEnvio() {
        return this.tipoDeEnvio;
    }

    public void agregarMercaderia(Mercaderia mercaderia) {
        this.listaDeMercaderia.add(mercaderia);
    }

    public String direccionDeEnvio() {
        return tipoDeEnvio.direccionDeEnvio();
    }

    public LocalDateTime turnoDeRetiro() {
        return this.tipoDeEnvio.horarioDeRetiro();
    }
}