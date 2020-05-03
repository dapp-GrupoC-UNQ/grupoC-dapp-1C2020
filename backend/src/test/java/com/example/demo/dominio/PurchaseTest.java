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
    public void unaCompraTieneUnMedioDePago(){
        Purchase purchase = PurchaseBuilder.aPurchase().withPaymentMethod("Efectivo").build();
        assertEquals("Efectivo", purchase.paymentMethod());
    }

    @Test
    public void aPourchaseHasATotal(){
        AdquiredProduct fideos = AdquiredProductBuilder.aProduct().withName("Fideos").withBrand("Knorr").withPrice(40.25).build();
        AdquiredProduct jugo = AdquiredProductBuilder.aProduct().withName("Jugo").withBrand("Tang").withPrice(40.25).withPrice(8.0).build();
        Purchase purchase = PurchaseBuilder.aPurchase().build();
        purchase.addAQuiredProduct(fideos);
        purchase.addAQuiredProduct(jugo);
        assertEquals(48.25, purchase.total());
    }

    @Test
    public void aPourchaseHasAStore(){
        Purchase purchase = PurchaseBuilder.aPurchase().withStore("Coto").build();
        assertEquals("Coto", purchase.store());
    }

    @Test
    public void unaCompraTieneUnRetiroEnLocalDebeTenerUnHorarioDeRetiro(){
        LocalDateTime hora = LocalDateTime.of(2020,4,25,10,0);
        StorePickUp storePickUp = new StorePickUp(hora);
        Purchase purchase = PurchaseBuilder.aPurchase().withDeliveryType(storePickUp).build();
        assertTrue(purchase.pickUpDate().isEqual(hora));
    }

    @Test
    public void siUnaCompraNoTieneProductosEnSuListaElMontoEsCero(){
        Purchase purchase = PurchaseBuilder.aPurchase().build();
        assertEquals(0.0, purchase.total());
    }

    @Test
    public void aPurchaseHasAUser(){
        Purchase purchase = PurchaseBuilder.aPurchase().withUser("pepe").build();
        assertEquals("pepe", purchase.user());
    }

    @Test
    public void siUnaCompraTieneEnvioADomicilioDebeTenerUnaDireccion(){
        HomeDelivery envio = new HomeDelivery("Alsina 123");
        Purchase purchase = PurchaseBuilder.aPurchase().withDeliveryType(envio).build();
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