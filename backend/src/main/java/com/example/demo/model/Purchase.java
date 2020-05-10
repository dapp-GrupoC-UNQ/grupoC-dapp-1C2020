package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Purchase {

    private String paymentMethod;
    private DeliveryType deliveryType;
    private Store purchaseStore;
    private User purchaseUser;
    private List<AdquiredProduct> productList = new ArrayList<>();

    public Purchase(String payment, DeliveryType delivery, Store store, User name){
        paymentMethod = payment;
        deliveryType = delivery;
        purchaseStore = store;
        purchaseUser = name;
    }

    public String paymentMethod(){
        return this.paymentMethod;
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

    public Store store() { return this.purchaseStore; }

    public User user() { return this.purchaseUser;  }

    public Integer productsQuantity() { return this.productList.stream().mapToInt(AdquiredProduct::quantity).sum();  }

    public List<AdquiredProduct> getListOfAdquiredProducts() {
        return this.productList;
    }

    public void addProduct(String productName, String productBrand, Integer quantity) {
        this.productList.add(this.store().getProduct(productName, productBrand, quantity));
    }
}