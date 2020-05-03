package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Purchase {

    private String paymentMethod;
    private TipoDeEnvio deliveryType;
    private String storeName;
    private List<AdquiredProduct> productList = new ArrayList<>();

    public Purchase(String payment, TipoDeEnvio delivery, String store){
        paymentMethod = payment;
        deliveryType = delivery;
        storeName = store;
    }

    public String paymentMethod(){
        return this.paymentMethod;
    }

    public Double total() {
            return this.productList.stream().mapToDouble(AdquiredProduct::price).sum();
    }

    public TipoDeEnvio deliveryType() {
        return this.deliveryType;
    }

    public String deliveryAddress() {
        return deliveryType.direccionDeEnvio();
    }

    public LocalDateTime pickUpDate() {
        return this.deliveryType.horarioDeRetiro();
    }


    public void addAQuiredProduct(AdquiredProduct product) {
        this.productList.add(product);
    }

    public String store() { return this.storeName; }
}