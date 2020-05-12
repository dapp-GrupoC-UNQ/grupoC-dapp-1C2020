package com.example.demo.dominio;

import com.example.demo.builders.PurchaseBuilder;
import com.example.demo.builders.TicketBuilder;
import com.example.demo.model.HomeDelivery;
import com.example.demo.model.Purchase;
import com.example.demo.model.StorePickUp;
import com.example.demo.model.exceptions.OptionNotAvailableForThisDeliveryType;
import com.example.demo.model.ticket.Ticket;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

public class TicketTest {

    @Test
    public void aTicketHasAPaymentMethod() {
        Ticket ticket = TicketBuilder.aTicket().withPaymentMethod("Credit card").build();
        assertEquals(ticket.paymentMethod(), "Credit card");
    }

    @Test
    public void aHomeDeliveryPurchaseTicketHasDeliveryAddress() {
        Ticket ticket = TicketBuilder.aTicket().withDeliveryMethod(new HomeDelivery("Alsina 123", LocalDateTime.now().plusDays(1))).build();
        assertEquals(ticket.addressOfDelivery(), "Alsina 123");
    }

    @Test
    public void aStorePickUpPurchaseTicketHasADeliveryTime() {
        Ticket ticket = TicketBuilder.aTicket().withDeliveryMethod(new StorePickUp(LocalDateTime.of(2020, 5, 13, 13, 45))).build();
        assertEquals(ticket.deliveryTime(), LocalDateTime.of(2020, 5, 13, 13, 45));
    }

    @Test
    public void aStoreDeliveryPurchaseTicketDoesNotHaveADeliveryAddress() {
        Ticket ticket = TicketBuilder.aTicket().withDeliveryMethod(new StorePickUp(LocalDateTime.of(2020, 5, 13, 13, 45))).build();
        assertThrows(OptionNotAvailableForThisDeliveryType.class, () -> ticket.addressOfDelivery());
    }

    @Test
    public void aTicketHasAPurchase() {
        Purchase purchase = PurchaseBuilder.aPurchase().build();
        Ticket ticket = TicketBuilder.aTicket().withPurchase(purchase).build();
        assertEquals(ticket.purchase(), purchase);
    }
}
