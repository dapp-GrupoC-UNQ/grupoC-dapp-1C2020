package model;

public class Compra {

    private String medioDePago;
    private Double montoTotal;
    private String tipoDeEnvio;

    public Compra(String pago, Double total, String envio){
        medioDePago = pago;
        montoTotal = total;
        tipoDeEnvio = envio;
    }

    public String medioDePago(){
        return this.medioDePago;
    }

    public Double montoTotal() {
        return this.montoTotal;
    }

    public String tipoEnvio() {
        return this.tipoDeEnvio;
    }
}