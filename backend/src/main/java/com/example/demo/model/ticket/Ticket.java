package com.example.demo.model.ticket;

import com.example.demo.model.DeliveryType;
import com.example.demo.model.Purchase;

public class Ticket {

    private Purchase ticketPurchase;
    private String paymentMethod;
    private DeliveryType deliveryType;

    public Ticket(Purchase purchase, String aPaymentMethod, DeliveryType aDeliveryType) {
        ticketPurchase = purchase;
        paymentMethod = aPaymentMethod;
        deliveryType = aDeliveryType;
    }

    public Purchase purchase() {
        return this.ticketPurchase;
    }

    public String addressOfDelivery() {
        return this.deliveryType.deliveryAddress();
    }
}
