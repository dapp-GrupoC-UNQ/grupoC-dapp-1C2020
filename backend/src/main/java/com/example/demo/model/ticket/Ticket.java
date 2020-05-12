package com.example.demo.model.ticket;

import com.example.demo.model.DeliveryType;
import com.example.demo.model.Purchase;
import com.example.demo.model.purchasePriceCalculator.PurchasePriceCalculator;

import java.time.LocalDateTime;

public class Ticket {

    private Purchase ticketPurchase;
    private String paymentMethod;
    private DeliveryType deliveryType;
    private Double totalPrice;

    public Ticket(Purchase purchase, String aPaymentMethod, DeliveryType aDeliveryType) {
        ticketPurchase = purchase;
        paymentMethod = aPaymentMethod;
        deliveryType = aDeliveryType;
        totalPrice = new PurchasePriceCalculator().calculatePriceFor(ticketPurchase);
    }

    public Purchase purchase() {
        return this.ticketPurchase;
    }

    public String addressOfDelivery() {
        return this.deliveryType.deliveryAddress();
    }

    public String paymentMethod() {
        return this.paymentMethod;
    }

    public LocalDateTime deliveryTime() {
        return this.deliveryType.pickUpDate();
    }
}
