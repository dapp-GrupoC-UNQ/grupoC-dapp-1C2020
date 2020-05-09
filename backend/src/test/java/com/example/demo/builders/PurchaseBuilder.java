package com.example.demo.builders;

import com.example.demo.model.*;

public class PurchaseBuilder {
    public static PurchaseBuilder aPurchase() {
        return new PurchaseBuilder();
    }

    private String paymentMethod = "Credit Card";
    private DeliveryType deliveryType = new HomeDelivery("Alsina 899");
    private Store purchaseStore = ComercioBuilder.unComercio().build();
    private User userName = UserBuilder.user().build();

    public Purchase build(){
        return new Purchase(paymentMethod, deliveryType, purchaseStore, userName);
    }

    public PurchaseBuilder withPaymentMethod(String payment) {
        paymentMethod = payment;
        return this;
    }

    public PurchaseBuilder withDeliveryType(DeliveryType delivery) {
        deliveryType = delivery;
        return this;
    }

    public PurchaseBuilder withDeliveryMethod(DeliveryType delivery) {
        deliveryType = delivery;
        return this;
    }

    public PurchaseBuilder withStore(Store store) {
        purchaseStore = store;
        return this;
    }

    public PurchaseBuilder withUser(User aUser) {
        userName = aUser;
        return this;
    }
}