package com.example.demo.builders;

import com.example.demo.model.DeliveryType;
import com.example.demo.model.HomeDelivery;
import com.example.demo.model.Purchase;
import com.example.demo.model.ticket.Ticket;

public class TicketBuilder {

    private String paymentMethod = "Credit Card";
    private Purchase ticketPurchase = PurchaseBuilder.aPurchase().build();
    private DeliveryType deliveryMethod = new HomeDelivery("Alsina 123");

    public static TicketBuilder aTicket() {
        return new TicketBuilder();
    }

    public Ticket build(){
        return new Ticket(ticketPurchase, paymentMethod, deliveryMethod);
    }

    public TicketBuilder withPaymentMethod(String aPaymentMethod) {
        paymentMethod = aPaymentMethod;
        return this;
    }

    public TicketBuilder withPurchase(Purchase purchase) {
        ticketPurchase = purchase;
        return this;
    }

    public TicketBuilder withDeliveryMethod(DeliveryType delivery) {
        deliveryMethod = delivery;
        return this;
    }
}