package com.example.demo.model.ticket;

import com.example.demo.model.Purchase;

public class Ticket {

    private Purchase ticketPurchase;

    public Ticket(Purchase purchase) {
        ticketPurchase = purchase;
    }

    public Purchase purchase() {
        return this.ticketPurchase;
    }
}
