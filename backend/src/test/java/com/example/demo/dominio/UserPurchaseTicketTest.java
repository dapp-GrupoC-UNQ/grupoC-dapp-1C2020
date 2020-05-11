package com.example.demo.dominio;

import com.example.demo.builders.PurchaseBuilder;
import com.example.demo.builders.UserBuilder;
import com.example.demo.model.Purchase;
import com.example.demo.model.User;
import com.example.demo.model.exceptions.OptionNotAvailableForThisDeliveryType;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertThrows;
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

    @Test
    public void aUserThatChoosesHomeDeliveryTicketHasAndAddressAndDeliveryDate() {
        User user = UserBuilder.user().build();
        Purchase purchase = PurchaseBuilder.aPurchase().withUser(user).build();
        purchase.finishPurchaseWithHomeDelivery("Credit Card", "Alsina 123");
        assertEquals(user.ticketOf(purchase).addressOfDelivery(), "Alsina 123");
        //asertEquals(acertar que el horario del ticket es el que le dio el store)
    }

    @Test
    public void aUserThatChoosesStorePickUpDeliveryTicketHasADeliveryDateAndDoesNotHaveADeliveryAddress() {
        User user = UserBuilder.user().build();
        Purchase purchase = PurchaseBuilder.aPurchase().withUser(user).build();
        purchase.finishPurchase("Credit Card");
        //ACERTAR QUE SEA EL TURNO QUE DA EL STORE
        assertEquals(user.ticketOf(purchase).deliveryTime(), LocalDateTime.of(2020, 5, 13, 13, 45));
        assertThrows(OptionNotAvailableForThisDeliveryType.class, () -> user.ticketOf(purchase).addressOfDelivery());
    }

}
