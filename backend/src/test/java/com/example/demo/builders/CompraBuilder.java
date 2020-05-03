package com.example.demo.builders;

import com.example.demo.model.Compra;
import com.example.demo.model.TipoDeEnvio;

public class CompraBuilder {
    public static CompraBuilder unaCompra() {
        return new CompraBuilder();
    }

    private String medioDePago;
    private TipoDeEnvio tipoDeEnvio;
    private String direccionDeEnvio;
    private String storeName;

    public Compra build(){
        return new Compra(medioDePago, tipoDeEnvio, storeName);
    }

    public CompraBuilder conMedioDePago(String tipoPago) {
        medioDePago = tipoPago;
        return this;
    }

    public CompraBuilder conTipoDeEnvio(TipoDeEnvio envio) {
        tipoDeEnvio = envio;
        return this;
    }

    public CompraBuilder conDireccionDeEnvio(String direccion) {
        direccionDeEnvio = direccion;
        return this;
    }

    public CompraBuilder withStore(String store) {
        storeName = store;
        return this;
    }
}