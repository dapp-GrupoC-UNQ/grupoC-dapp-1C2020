package com.example.demo.dominio;

import com.example.demo.builders.ComercioBuilder;
import com.example.demo.builders.DiscountBuilder;
import com.example.demo.model.PercentageDiscount;
import com.example.demo.model.Store;
import com.example.demo.model.Discount;
import com.example.demo.model.excepciones.NotFoundProductInStore;
import com.example.demo.model.excepciones.RepeatedMerchandiseInStore;
import com.example.demo.model.merchandise.Merchandise;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MerchandiseInStoreTest {

    @Test
    public void whenAStoreIsNewIsDoesNotHaveMerchandise() {
        Store newStore = ComercioBuilder.unComercio().build();
        assertFalse(newStore.hasMerchandises());
    }

    @Test
    public void whenAStoreAddsANewMerchandiseItNowBelongsToTheStore() {
        Store store = ComercioBuilder.unComercio().build();
        store.addMerchandise("Fideos", "Marolio", 34.45, 23);
        assertTrue(store.sellsMerchandise("Fideos", "Marolio"));
        assertEquals(store.stockOf("Fideos", "Marolio"), 23);
    }

    @Test
    public void aStoreKnowsThePriceOfAProductItSells() {
        Store store = ComercioBuilder.unComercio().build();
        store.addMerchandise("Fideos", "Marolio", 34.45, 23);
        assertEquals(store.priceOf("Fideos", "Marolio"), 34.45);
    }

    @Test
    public void aStoreDoesNotKnowThePriceOfAProductItDoesNotSell() {
        Store store = ComercioBuilder.unComercio().build();
        assertThrows(NotFoundProductInStore.class, () -> store.priceOf("Un producto", "Que no existe"));
    }

    @Test
    public void aStoreKnowsTheStockOfAProductItSells() {
        Store store = ComercioBuilder.unComercio().build();
        store.addMerchandise("Fideos", "Marolio", 34.45, 23);
        assertEquals(store.stockOf("Fideos", "Marolio"), 23);
    }

    @Test
    public void aStoreDoesNotKnowTheStockOfAProductItDoesNotSell() {
        Store store = ComercioBuilder.unComercio().build();
        assertThrows(NotFoundProductInStore.class, () -> store.stockOf("Un producto", "Que no existe"));
    }


    @Test
    public void aStoreCannotAddTheSameProductTwice() {
        //por mismo producto se entiende mismo nombre y marca
        Store store = ComercioBuilder.unComercio().build();
        store.addMerchandise("Fideos", "Marolio", 34.45, 23);
        assertThrows(RepeatedMerchandiseInStore.class, () -> store.addMerchandise("Fideos", "Marolio", 34.45, 23));
    }

    @Test
    public void aStoreCanUpdateThePriceOfAnExistingProduct() {
        Store store = ComercioBuilder.unComercio().build();
        store.addMerchandise("Fideos", "Marolio", 34.45, 23);
        store.updatePriceFor("Fideos", "Marolio", 36.45);
        assertEquals(store.priceOf("Fideos", "Marolio"), 36.45);
    }

    @Test
    public void aStoreCanNotUpdateThePriceOfAnExistingProduct() {
        Store store = ComercioBuilder.unComercio().build();
        assertThrows(NotFoundProductInStore.class, () -> store.updatePriceFor("Fideos", "Marolio", 36.45));
    }

    @Test
    public void aStoreCanAddStockForAnExistingProduct() {
        Store store = ComercioBuilder.unComercio().build();
        store.addMerchandise("Fideos", "Marolio", 34.45, 23);
        store.addStock("Fideos", "Marolio", 20);
        assertEquals(store.stockOf("Fideos", "Marolio"), 43);
    }

    @Test
    public void aStoreCanNotAddStockForANonExistingProduct() {
        Store store = ComercioBuilder.unComercio().build();
        assertThrows(NotFoundProductInStore.class, () -> store.addStock("Fideos", "Marolio", 10));
    }

    @Test
    public void aStoreApplyADiscountToAProduct(){
        Store store = ComercioBuilder.unComercio().build();
        PercentageDiscount discount = new PercentageDiscount(50, LocalDate.of(2020,5,1), LocalDate.of(2020, 5, 20));
        store.addMerchandise("Mayonesa", "Hellmans", 20.0, 200);
        Merchandise merchandise = store.getMerchandise("Mayonesa", "Hellmans");
        store.applyDiscountOn(merchandise, discount);
        assertTrue(merchandise.hasADiscount());
        assertEquals(50, merchandise.percentOfDiscount());
        assertEquals(10.0, merchandise.price());
    }

    @Test
    public void unComercioPuedeDecrementarStockParaUnProductoExistente() {
        Store store = ComercioBuilder.unComercio().build();
        Discount noDiscount = DiscountBuilder.aDiscount().buildNoDiscount();
        store.addMerchandise("Fideos", "Marolio", 34.45, 23);
        store.decreaseStock("Fideos", "Marolio", 20);
        assertEquals(store.stockOf("Fideos", "Marolio"), 3);
    }

    @Test
    public void unComercioNoPuedeDecrementarStockParaUnProductoInexistente() {
        Store store = ComercioBuilder.unComercio().build();
        assertThrows(NotFoundProductInStore.class, () -> store.decreaseStock("Fideos", "Marolio", 10));
    }
}
