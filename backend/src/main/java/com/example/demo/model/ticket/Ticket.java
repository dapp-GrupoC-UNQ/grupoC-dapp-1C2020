package com.example.demo.model.ticket;

import com.example.demo.model.DeliveryType;
import com.example.demo.model.PurchaseFromStore;
import com.example.demo.model.purchasePriceCalculator.PurchasePriceCalculator;

import java.time.LocalDateTime;

public class Ticket {

    private PurchaseFromStore ticketPurchase;
    private String paymentMethod;
    private Double totalPrice;
    private DeliveryType deliveryType;

    public Ticket(PurchaseFromStore purchase, String aPaymentMethod, DeliveryType delivery) {
        ticketPurchase = purchase;
        paymentMethod = aPaymentMethod;
        totalPrice = new PurchasePriceCalculator().calculatePriceFor(ticketPurchase);
        deliveryType = delivery;
    }

    public PurchaseFromStore purchase() {
        return this.ticketPurchase;
    }

    public String paymentMethod() {
        return this.paymentMethod;
    }

    public String addressOfDelivery() {
        return this.deliveryType.deliveryAddress();
    }

    public LocalDateTime deliveryTime() {
        return this.deliveryType.pickUpDate();
    }

}
