package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Compra {

    private String medioDePago;
    private TipoDeEnvio tipoDeEnvio;
    private String storeName;
    private List<AdquiredProduct> productList = new ArrayList<>();

    public Compra(String pago, TipoDeEnvio envio,String store){
        medioDePago = pago;
        tipoDeEnvio = envio;
        storeName = store;
    }

    public String medioDePago(){
        return this.medioDePago;
    }

    public Double total() {
            return this.productList.stream().mapToDouble(AdquiredProduct::price).sum();
    }

    public TipoDeEnvio tipoEnvio() {
        return this.tipoDeEnvio;
    }

    public String direccionDeEnvio() {
        return tipoDeEnvio.direccionDeEnvio();
    }

    public LocalDateTime turnoDeRetiro() {
        return this.tipoDeEnvio.horarioDeRetiro();
    }


    public void addAQuiredProduct(AdquiredProduct product) {
        this.productList.add(product);
    }

    public String store() { return this.storeName; }
}