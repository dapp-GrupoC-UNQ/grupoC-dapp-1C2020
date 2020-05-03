package com.example.demo.dominio;

import com.example.demo.builders.AdquiredProductBuilder;
import com.example.demo.builders.PurchaseBuilder;
import com.example.demo.model.*;
import com.example.demo.model.excepciones.OptionNotAvailableForThisDeliveryType;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class PurchaseTest {

    @Test
    public void aPurchaseHasAPaymentMethod(){
        Purchase purchase = PurchaseBuilder.aPurchase().withPaymentMethod("Efectivo").build();
        assertEquals("Efectivo", purchase.paymentMethod());
    }

    @Test
    public void aPourchaseHasATotal(){
        AdquiredProduct fideos = AdquiredProductBuilder.aProduct().withName("Fideos").withBrand("Knorr").withPrice(40.25).withQuantity(1).build();
        AdquiredProduct jugo = AdquiredProductBuilder.aProduct().withName("Jugo").withBrand("Tang").withPrice(8.0).withQuantity(2).build();
        Purchase purchase = PurchaseBuilder.aPurchase().build();
        purchase.addAQuiredProduct(fideos);
        purchase.addAQuiredProduct(jugo);
        assertEquals(56.25, purchase.total());
        assertEquals(3, purchase.productsQuantity());
    }

    @Test
    public void aPourchaseHasAStore(){
        Purchase purchase = PurchaseBuilder.aPurchase().withStore("Coto").build();
        assertEquals("Coto", purchase.store());
    }

    @Test
    public void aPurchaseWithAStorePickUpShouldHaveAPickUpDate(){
        LocalDateTime hora = LocalDateTime.of(2020,4,25,10,0);
        StorePickUp storePickUp = new StorePickUp(hora);
        Purchase purchase = PurchaseBuilder.aPurchase().withDeliveryType(storePickUp).build();
        assertTrue(purchase.pickUpDate().isEqual(hora));
    }

    @Test
    public void ifAPurchaseHastNotProductsTotalPriceIsZero(){
        Purchase purchase = PurchaseBuilder.aPurchase().build();
        assertEquals(0.0, purchase.total());
    }

    @Test
    public void aPurchaseHasAUser(){
        Purchase purchase = PurchaseBuilder.aPurchase().withUser("pepe").build();
        assertEquals("pepe", purchase.user());
    }

    @Test
    public void ifAPurchaseHasHomeDeliveryShouldHaveAnAdrress(){
        HomeDelivery delivery = new HomeDelivery("Alsina 123");
        Purchase purchase = PurchaseBuilder.aPurchase().withDeliveryType(delivery).build();
        assertEquals("Alsina 123", purchase.deliveryAddress());
    }

    @Test
    public void  aPurchaseWithStorePickUpHasNoAddress(){
        LocalDateTime hour = LocalDateTime.of(2020,4,25,10,0);
        StorePickUp storePickUp = new StorePickUp(hour);
        Purchase purchase = PurchaseBuilder.aPurchase().withDeliveryType(storePickUp).build();
        assertThrows(OptionNotAvailableForThisDeliveryType.class, () -> purchase.deliveryAddress());
    }

    @Test
    public void aPurchaseWithDeliveryHasNotAPickUpDate(){
        HomeDelivery delivery = new HomeDelivery("Alsina 123");
        Purchase purchase = PurchaseBuilder.aPurchase().withDeliveryType(delivery).build();
        assertThrows(OptionNotAvailableForThisDeliveryType.class, () -> purchase.pickUpDate());
    }

}