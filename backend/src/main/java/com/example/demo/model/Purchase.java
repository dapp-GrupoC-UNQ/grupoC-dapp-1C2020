package com.example.demo.model;

import com.example.demo.model.ticket.Ticket;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Purchase {

    private Store purchaseStore;
    private User purchaseUser;
    private List<AdquiredProduct> productList = new ArrayList<>();

    public Purchase(Store store, User name){
        purchaseStore = store;
        purchaseUser = name;
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
        purchaseUser.addTicketOfPurchase(new Ticket(this, paymentMethod, new StorePickUp(LocalDateTime.now())));
    }

    public void finishPurchaseWithHomeDelivery(String paymentMethod, String deliveryAddress) {
        purchaseUser.addTicketOfPurchase(new Ticket(this, paymentMethod, new HomeDelivery(deliveryAddress)));
    }
}