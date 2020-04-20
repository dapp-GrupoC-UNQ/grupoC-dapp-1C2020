package model;

import java.util.ArrayList;
import java.util.List;

public class Compra {

    private String medioDePago;
    private String tipoDeEnvio;
    private List<Mercaderia> listaDeMercaderia = new ArrayList<>();

    public Compra(String pago, String envio){
        medioDePago = pago;
        tipoDeEnvio = envio;
    }

    public String medioDePago(){
        return this.medioDePago;
    }

    public Double montoTotal() {
       return this.listaDeMercaderia.stream().mapToDouble(Mercaderia::precio).sum();
    }

    public String tipoEnvio() {
        return this.tipoDeEnvio;
    }

    public void agregarMercaderia(Mercaderia mercaderia) {
        this.listaDeMercaderia.add(mercaderia);
    }
}