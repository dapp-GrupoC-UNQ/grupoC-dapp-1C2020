package com.example.demo.model.store;

import com.example.demo.builders.StoreBuilder;
import com.example.demo.model.AcquiredProduct;
import com.example.demo.model.RangoHorarioComercio;
import com.example.demo.model.Store;
import com.example.demo.model.exceptions.InsufficientMerchandiseStockException;
import com.example.demo.model.exceptions.NotFoundProductInStore;
import com.example.demo.model.merchandise.MerchandiseCategory;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class StoreTest {

    @Test
    public void unComercioTieneUnNombre() {
        Store store = StoreBuilder.aStore().withName("Disco").build();
        assertEquals(store.name(), "Disco");
    }

    @Test
    public void unComercioTieneUnaDireccion() {
        Store store = StoreBuilder.aStore().withAddress("Calchaqui 423").build();
        assertEquals(store.address(), "Calchaqui 423");
    }

    @Test
    public void unComercioTieneUnRubro() {
        //podrian ser varios?
        Store store = StoreBuilder.aStore().withCategory("Supermercado").build();
        assertEquals(store.storeCategory(),  "Supermercado");
    }

    @Test
    public void unComercioTieneUnaDistanciaMaximaDeDelivery() {
        Store store = StoreBuilder.aStore().withDeliveryMaxDistance(4).build();
        assertEquals(store.deliveryDistanceInKm(),  4);
    }

    @Test
    public void unComercioTieneUnMedioDePago() {
        List<String> mediosDePago = Arrays.asList("Efectivo");
        Store store = StoreBuilder.aStore().conMediosDePago(mediosDePago).build();
        assertEquals(store.amountOfAvailablePaymentMethods(),  1);
    }

    @Test
    public void unComercioTieneUnDeterminadoMedioDePago() {
        List<String> mediosDePago = Arrays.asList("Efectivo","Tarjeta de debito");
        Store store = StoreBuilder.aStore().conMediosDePago(mediosDePago).build();
        assertTrue(store.canPayWith("Tarjeta de debito"));
    }

    @Test
    public void unComercioNoPuedeAtenderEnUnHorarioFueraDeSuRango() {
        List<RangoHorarioComercio> horarioComercio = Arrays.asList(new RangoHorarioComercio(DayOfWeek.FRIDAY, LocalTime.of(9,0), LocalTime.of(13, 0)));
        Store store = StoreBuilder.aStore().withPaymentMethods(horarioComercio).build();
        assertFalse(store.isOpenAt(DayOfWeek.FRIDAY, LocalTime.of(20,0)));
    }

    @Test
    public void paymentMethodNotAvailableForStore(){
        List<String> payment = Arrays.asList("Cash");
        Store store = StoreBuilder.aStore().conMediosDePago(payment).build();
        assertFalse(store.canPayWith("Credit card"));
    }

    @Test
    public void getProductReturnsAnAcquiredProductAndDecreasesTheProductStock() {
        Store store = StoreBuilder.aStore().build();
        store.addMerchandise("Mayonesa", "Hellmans", 15.4, 200, MerchandiseCategory.GROCERY, "foto mayonesa");
        AcquiredProduct product = store.getProduct("Mayonesa", "Hellmans", 2);
        assertEquals(store.stockOf("Mayonesa", "Hellmans"), 198);
        assertEquals(product.quantity(), 2);
        assertEquals(product.price(), 15.4);
        assertEquals(product.brand(), "Hellmans");
        assertEquals(product.name(), "Mayonesa");
    }

    @Test
    public void itIsNotPossibleToGetAProductIfThereIsNotEnoughStock() {
        Store store = StoreBuilder.aStore().build();
        store.addMerchandise("Mayonesa", "Hellmans", 15.4, 200, MerchandiseCategory.GROCERY, "foto mayonesa");
        assertThrows(InsufficientMerchandiseStockException.class, () -> store.getProduct("Mayonesa", "Hellmans", 300));
    }

    @Test
    public void itIsNotPossibleToGetAProductIfItDoesNotExistInTheStore() {
        Store store = StoreBuilder.aStore().build();
        store.addMerchandise("Mayonesa", "Hellmans", 15.4, 200, MerchandiseCategory.GROCERY, "foto mayonesa");
        assertThrows(NotFoundProductInStore.class, () -> store.getProduct("A fake", "Product", 1));
    }
}
