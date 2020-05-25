package com.example.demo.model.ticket;

import com.example.demo.builders.PurchaseFromStoreBuilder;
import com.example.demo.builders.UserBuilder;
import com.example.demo.model.*;
import com.example.demo.model.purchase.Bill;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class BillGeneratorTest {

    @Test
    public void unUsuarioRealizaUnaCompraYSeGeneraLaBillConSuTicketCorrespondiente(){
        User pepe = UserBuilder.user().build();
        PurchaseFromStore purchase = PurchaseFromStoreBuilder.aPurchase().build();
        String paymentMethod = "Tarjeta de credito";
        DeliveryType deliveryType = new HomeDelivery("Alsina 123", LocalDateTime.now().plusDays(1));
        BillGenerator billGenerator = new BillGenerator();
        billGenerator.generateBill(Arrays.asList(purchase), pepe, paymentMethod, deliveryType);
        assertTrue(pepe.hasABill());
        assertEquals(1, pepe.quantityOfBills());
    }

    @Test
    public void unUsuarioRealizaUnaCompraEncomerciosDistintosComerciosYSeGeneranLosTicketsCorrespondientes(){
        User pepe = UserBuilder.user().build();
        PurchaseFromStore aPurchase = PurchaseFromStoreBuilder.aPurchase().build();
        PurchaseFromStore anotherPurchase = PurchaseFromStoreBuilder.aPurchase().build();
        String paymentMethod = "Tarjeta de credito";
        DeliveryType deliveryType = new HomeDelivery("Alsina 123", LocalDateTime.now().plusDays(1));
        BillGenerator billGenerator = new BillGenerator();
        Bill bill = billGenerator.generateBill(Arrays.asList(aPurchase, anotherPurchase), pepe, paymentMethod, deliveryType);
        assertTrue(pepe.hasABill());
        assertEquals(2, bill.quantityTickets());
    }
}
