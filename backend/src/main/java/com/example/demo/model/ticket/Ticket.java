package com.example.demo.model.ticket;

import com.example.demo.model.Purchase;

public class Ticket {

    private Purchase ticketPurchase;
    private String paymentMethod;

    public Ticket(Purchase purchase, String aPaymentMethod) {
        ticketPurchase = purchase;
        paymentMethod = aPaymentMethod;
    }

    public Purchase purchase() {
        return this.ticketPurchase;
    }
}
