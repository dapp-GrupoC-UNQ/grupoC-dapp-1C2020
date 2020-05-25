package com.example.demo.model.ticket;

import com.example.demo.builders.BillBuilder;
import com.example.demo.builders.TicketBuilder;
import com.example.demo.model.purchase.Bill;
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
        Bill bill = BillBuilder.aBill().build();
        assertEquals("Alsina 123", bill.addressOfDelivery());
        assertEquals(LocalDateTime.of(2020, 05, 30, 18,0), bill.deliveryTime());
    }

   /* @Test
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
        PurchasePriceCalculator calculator = new PurchasePriceCalculator();
        Double total = (aPrice * aQuantity) + (anotherPrice * anotherQuantity);
        assertEquals(total, calculator.calculatePriceForMultiPurchase(bill));
        assertEquals(aQuantity + anotherQuantity, bill.totalProductsQuantity());
    }*/

}
