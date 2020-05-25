package com.example.demo.model.ticket;

import com.example.demo.builders.BillBuilder;
import com.example.demo.builders.PurchaseFromStoreBuilder;
import com.example.demo.builders.StoreBuilder;
import com.example.demo.builders.TicketBuilder;
import com.example.demo.model.DeliveryType;
import com.example.demo.model.HomeDelivery;
import com.example.demo.model.PurchaseFromStore;
import com.example.demo.model.merchandise.MerchandiseCategory;
import com.example.demo.model.purchase.Bill;
import com.example.demo.model.store.Store;
import com.example.demo.model.ticket.Ticket;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Arrays;

public class BillTest {

    @Test
    public void aBillHasTwoTickets(){
        Ticket ticket1 = TicketBuilder.aTicket().build();
        Ticket ticket2 = TicketBuilder.aTicket().build();
        Bill bill = BillBuilder.aBill().withTickets(Arrays.asList(ticket1, ticket2)).build();
        assertEquals(2, bill.quantityTickets());
    }

    @Test
    public void aBillHasAHomeDelivery(){
        DeliveryType delivery = new HomeDelivery("Alsina 123", LocalDateTime.of(2020, 05, 20, 18, 00));
        Bill bill = BillBuilder.aBill().withDeliveyType(delivery).build();
        assertEquals("Alsina 123", bill.addressOfDelivery());
        assertEquals(LocalDateTime.of(2020, 05, 20, 18, 00), bill.deliveryTime());
    }

    @Test
    public void aBillTotalIsEqualsOfSumOfAllTickets(){
        Double aPrice = 15.8;
        Double anotherPrice = 32.5;
        Integer aQuantity = 3;
        Integer anotherQuantity = 2;
        Store storeWithProducts = StoreBuilder.withMerchandise("Mayonesa", "Hellmans", aPrice, aQuantity + 1, MerchandiseCategory.GROCERY);
        Store anotherStoreWithProducts = StoreBuilder.withMerchandise("Fideos", "Marolio", anotherPrice, anotherQuantity + 1, MerchandiseCategory.GROCERY);
        PurchaseFromStore purchase1 = PurchaseFromStoreBuilder.aPurchase().withProductOfStore("Mayonesa", "Hellmans", aQuantity, storeWithProducts);
        PurchaseFromStore purchase2 = PurchaseFromStoreBuilder.aPurchase().withProductOfStore("Fideos", "Marolio", anotherQuantity, anotherStoreWithProducts);
        Ticket ticket1 = TicketBuilder.aTicket().withPurchase(purchase1).build();
        Ticket ticket2 = TicketBuilder.aTicket().withPurchase(purchase2).build();
        Bill bill = BillBuilder.aBill().withTickets(Arrays.asList(ticket1, ticket2)).build();
        Double total = (aPrice * aQuantity) + (anotherPrice * anotherQuantity);
        assertEquals(total, bill.totalPrice());
    }

}
