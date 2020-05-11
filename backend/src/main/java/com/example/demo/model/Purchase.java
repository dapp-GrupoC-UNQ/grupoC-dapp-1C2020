package com.example.demo.model;

import com.example.demo.model.ticket.Ticket;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Purchase {

    private DeliveryType deliveryType;
    private Store purchaseStore;
    private User purchaseUser;
    private List<AdquiredProduct> productList = new ArrayList<>();

    public Purchase(DeliveryType delivery, Store store, User name){
        deliveryType = delivery;
        purchaseStore = store;
        purchaseUser = name;
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

    public Boolean breaksMoneyThreshold() {
        return this.user().moneyThreshold().breaksTheLimitWith(this);
    }

    public void finishPurchase(String paymentMethod) {
        purchaseUser.addTicketOfPurchase(new Ticket(this, paymentMethod));
    }
}