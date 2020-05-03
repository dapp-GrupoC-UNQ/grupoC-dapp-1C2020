package com.example.demo.builders;

import com.example.demo.model.Purchase;
import com.example.demo.model.TipoDeEnvio;

public class PurchaseBuilder {
    public static PurchaseBuilder aPurchase() {
        return new PurchaseBuilder();
    }

    private String paymentMethod;
    private TipoDeEnvio deliveryType;
    private String deliveryAddress;
    private String storeName;

    public Purchase build(){
        return new Purchase(paymentMethod, deliveryType, storeName);
    }

    public PurchaseBuilder withPaymentMethod(String tipoPago) {
        paymentMethod = tipoPago;
        return this;
    }

    public PurchaseBuilder withDeliveryType(TipoDeEnvio delivery) {
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
}