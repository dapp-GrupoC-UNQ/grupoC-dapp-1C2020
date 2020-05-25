package com.example.demo.builders;

import com.example.demo.model.DeliveryType;
import com.example.demo.model.HomeDelivery;
import com.example.demo.model.purchase.Bill;
import com.example.demo.model.ticket.Ticket;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class BillBuilder {
    
    private List<Ticket> multiPurchases = Arrays.asList(TicketBuilder.aTicket().build());
    private DeliveryType deliveryType = new HomeDelivery("Alsina 123", LocalDateTime.of(2020, 05,30,18,0));

    public static BillBuilder aBill() {  return new BillBuilder();   }

    public BillBuilder withTickets(List<Ticket> tickets) {
        multiPurchases = tickets;
        return this;
    }

    public Bill build() {
        return new Bill(multiPurchases, deliveryType);
    }

    public BillBuilder withDeliveyType(DeliveryType delivery) {
        deliveryType = delivery;
        return this;
    }
}
