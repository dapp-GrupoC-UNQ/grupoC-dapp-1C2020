package com.example.demo.model.purchase;

import com.example.demo.model.DeliveryType;
import com.example.demo.model.ticket.Ticket;

import java.time.LocalDateTime;
import java.util.List;

public class Bill {

    private List<Ticket> allTickets;
    private DeliveryType deliveryType;

    public Bill(List<Ticket> tickets, DeliveryType delivery){
        this.allTickets = tickets;
        this.deliveryType = delivery;
    }

    public Integer quantityTickets() {
        return this.allTickets.size();
    }

    public List<Ticket> getTickets() {
        return this.allTickets;
    }

    public String addressOfDelivery() {
        return this.deliveryType.deliveryAddress();
    }

    public LocalDateTime deliveryTime() {
        return this.deliveryType.pickUpDate();
    }

    public Double totalPrice() {
        return allTickets.stream().mapToDouble(Ticket::getTotal).sum();
    }

   /* public Integer totalProductsQuantity() {
        return allTickets.stream().mapToInt(PurchaseFromStore::productsQuantity).sum();
    }*/

}
