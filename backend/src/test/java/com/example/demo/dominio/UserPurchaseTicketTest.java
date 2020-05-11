package com.example.demo.dominio;

import com.example.demo.builders.PurchaseBuilder;
import com.example.demo.builders.UserBuilder;
import com.example.demo.model.Purchase;
import com.example.demo.model.User;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;


public class UserPurchaseTicketTest {

    @Test
    public void aUserThatHasNotMadePurchasesDoesNotHaveTickets() {
        User user = UserBuilder.user().build();
        assertFalse(user.hasTickets());
    }

    @Test
    public void aUserThatHasMadeAPurchaseHasThePurchaseTicketSaved() {
        User user = UserBuilder.user().build();
        Purchase purchase = PurchaseBuilder.aPurchase().withUser(user).build();
        purchase.finishPurchase("Credit Card");
        assertTrue(user.hasTicketOf(purchase));
    }


}
