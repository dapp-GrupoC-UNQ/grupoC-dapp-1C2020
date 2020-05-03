package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Purchase {

    private String paymentMethod;
    private DeliveryType deliveryType;
    private String storeName;
    private String userName;
    private List<AdquiredProduct> productList = new ArrayList<>();

    public Purchase(String payment, DeliveryType delivery, String store,String name){
        paymentMethod = payment;
        deliveryType = delivery;
        storeName = store;
        userName = name;
    }

    public String paymentMethod(){
        return this.paymentMethod;
    }

    public Double total() {
            return this.productList.stream().mapToDouble(AdquiredProduct::price).sum();
    }

    public DeliveryType deliveryType() {
        return this.deliveryType;
    }

    public String deliveryAddress() {
        return deliveryType.deliveryAddress();
    }

    public LocalDateTime pickUpDate() {
        return this.deliveryType.pickUpDate();
    }


    public void addAQuiredProduct(AdquiredProduct product) {
        this.productList.add(product);
    }

    public String store() { return this.storeName; }

    public String user() { return this.userName;  }
}