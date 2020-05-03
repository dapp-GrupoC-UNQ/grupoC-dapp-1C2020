package com.example.demo.builders;

import com.example.demo.model.Purchase;
import com.example.demo.model.DeliveryType;

public class PurchaseBuilder {
    public static PurchaseBuilder aPurchase() {
        return new PurchaseBuilder();
    }

    private String paymentMethod;
    private DeliveryType deliveryType;
    private String deliveryAddress;
    private String storeName;
    private String userName;

    public Purchase build(){
        return new Purchase(paymentMethod, deliveryType, storeName, userName);
    }

    public PurchaseBuilder withPaymentMethod(String payment) {
        paymentMethod = payment;
        return this;
    }

    public PurchaseBuilder withDeliveryType(DeliveryType delivery) {
        deliveryType = delivery;
        return this;
    }

    public PurchaseBuilder withDeliveryAddress(String address) {
        deliveryAddress = address;
        return this;
    }

    public PurchaseBuilder withStore(String store) {
        storeName = store;
        return this;
    }

    public PurchaseBuilder withUser(String name) {
        userName = name;
        return this;
    }
}