package com.example.demo.model.user;

import com.example.demo.builders.UserBuilder;
import com.example.demo.model.User;
import org.junit.Test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;


public class UserPurchaseTicketTest {

    @Test
    public void aUserThatHasNotMadePurchasesDoesNotHaveTickets() {
        User user = UserBuilder.user().build();
        assertFalse(user.hasTickets());
    }
/*
    @Test
    public void aUserThatHasMadeAPurchaseHasThePurchaseTicketSaved() {
        User user = UserBuilder.user().build();
        PurchaseFromStore purchase = PurchaseFromStoreBuilder.aPurchase().withUser(user).build();
        purchase.finishPurchase("Credit Card");
        assertTrue(user.hasTicketOf(purchase));
    }

    @Test
    public void aUserThatChoosesHomeDeliveryTicketHasAndAddressAndDeliveryDate() {
        User user = UserBuilder.user().build();
        PurchaseFromStore purchase = PurchaseFromStoreBuilder.aPurchase().withUser(user).build();
        purchase.finishPurchaseWithHomeDelivery("Credit Card", "Alsina 123");
        Ticket ticket = user.ticketOf(purchase);
        assertEquals(ticket.addressOfDelivery(), "Alsina 123");
    }

    @Test
    public void aUserThatChoosesStorePickUpDeliveryTicketDoesNotHaveADeliveryAddress() {
        User user = UserBuilder.user().build();
        PurchaseFromStore purchase = PurchaseFromStoreBuilder.aPurchase().withUser(user).build();
        purchase.finishPurchase("Credit Card");
        assertThrows(OptionNotAvailableForThisDeliveryType.class, () -> user.ticketOf(purchase).addressOfDelivery());
    }*/

}
