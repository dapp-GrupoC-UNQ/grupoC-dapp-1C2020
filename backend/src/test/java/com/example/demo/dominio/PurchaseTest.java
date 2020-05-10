package com.example.demo.dominio;

import com.example.demo.builders.ComercioBuilder;
import com.example.demo.builders.PurchaseBuilder;
import com.example.demo.builders.UserBuilder;
import com.example.demo.model.*;
import com.example.demo.model.excepciones.InsufficientMerchandiseStockException;
import com.example.demo.model.excepciones.NotFoundProductInStore;
import com.example.demo.model.excepciones.OptionNotAvailableForThisDeliveryType;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

public class PurchaseTest {

    @Test
    public void aPurchaseHasAPaymentMethod(){
        Purchase purchase = PurchaseBuilder.aPurchase().withPaymentMethod("Efectivo").build();
        assertEquals("Efectivo", purchase.paymentMethod());
    }

    @Test
    public void aNewPurchaseHasNoProducts() {
        Purchase purchase = PurchaseBuilder.aPurchase().build();
        assertEquals(purchase.productsQuantity(), 0);
    }

    @Test
    public void aPurchaseHasAStore(){
        Store coto = ComercioBuilder.unComercio().build();
        Purchase purchase = PurchaseBuilder.aPurchase().withStore(coto).build();
        assertEquals(coto, purchase.store());
    }

    @Test
    public void aPurchaseHasAUser(){
        User pepe = UserBuilder.user().build();
        Purchase purchase = PurchaseBuilder.aPurchase().withUser(pepe).build();
        assertEquals(pepe, purchase.user());
    }


    @Test
    public void isNotPossibleToAddAProductThatIsNotAvailableInTheStore(){
        Purchase purchase = PurchaseBuilder.aPurchase().build();
        assertThrows(NotFoundProductInStore.class, () -> { purchase.addProduct("Mayonesa", "Hellmans", 3); });
    }

    @Test
    public void isNotPossibleToAddAProductIfTheStoreCannotSatisfyTheStock(){
        Store storeWithProducts = ComercioBuilder.unComercio().build();
        storeWithProducts.addMerchandise("Mayonesa", "Hellmans", 15.8, 1);
        Purchase purchase = PurchaseBuilder.aPurchase().withStore(storeWithProducts).build();
        assertThrows(InsufficientMerchandiseStockException.class, () -> { purchase.addProduct("Mayonesa", "Hellmans", 3); });
    }

    @Test
    public void aPurchaseWithAStorePickUpShouldHaveAPickUpDate(){
        LocalDateTime hora = LocalDateTime.of(2020,4,25,10,0);
        StorePickUp storePickUp = new StorePickUp(hora);
        Purchase purchase = PurchaseBuilder.aPurchase().withDeliveryType(storePickUp).build();
        assertTrue(purchase.pickUpDate().isEqual(hora));
    }


    @Test
    public void ifAPurchaseHasHomeDeliveryItShouldHaveAnAdrress(){
        HomeDelivery delivery = new HomeDelivery("Alsina 123");
        Purchase purchase = PurchaseBuilder.aPurchase().withDeliveryType(delivery).build();
        assertEquals("Alsina 123", purchase.deliveryAddress());
    }

    @Test
    public void  aPurchaseWithStorePickUpDoesNotHaveAnAddress(){
        LocalDateTime hour = LocalDateTime.of(2020,4,25,10,0);
        StorePickUp storePickUp = new StorePickUp(hour);
        Purchase purchase = PurchaseBuilder.aPurchase().withDeliveryType(storePickUp).build();
        assertThrows(OptionNotAvailableForThisDeliveryType.class, purchase::deliveryAddress);
    }

    @Test
    public void aPurchaseWithDeliveryDoesNotHaveAPickUpDate(){
        HomeDelivery delivery = new HomeDelivery("Alsina 123");
        Purchase purchase = PurchaseBuilder.aPurchase().withDeliveryType(delivery).build();
        assertThrows(OptionNotAvailableForThisDeliveryType.class, purchase::pickUpDate);
    }

    @Test
    public void aPurchaseWithAHigherPriceThanTheUserMoneyThresholdBreaksTheLimit() {
        Store store = ComercioBuilder.withMerchandise("Mayonesa", "Hellmans", 15.0, 400);
        User userWithMoneyThreshold = UserBuilder.user().withMoneyThreshold(20.0);
        Purchase purchase = PurchaseBuilder.aPurchase().withUser(userWithMoneyThreshold).withProductOfStore("Mayonesa", "Hellmans", 10, store);
        assertTrue(purchase.breaksMoneyThreshold());
    }

    @Test
    public void aPurchaseWithALowerPriceThanTheUserMoneyThresholdDoesNotBreakTheLimit() {
        Store store = ComercioBuilder.withMerchandise("Mayonesa", "Hellmans", 15.0, 400);
        User userWithMoneyThreshold = UserBuilder.user().withMoneyThreshold(20000.0);
        Purchase purchase = PurchaseBuilder.aPurchase().withUser(userWithMoneyThreshold).withProductOfStore("Mayonesa", "Hellmans", 10, store);
        assertFalse(purchase.breaksMoneyThreshold());
    }
}