package com.example.demo.model.store;

import com.example.demo.builders.StoreBuilder;
import com.example.demo.builders.DiscountBuilder;
import com.example.demo.model.Store;
import com.example.demo.model.discounts.Discount;
import com.example.demo.model.exceptions.NotFoundProductInStore;
import com.example.demo.model.exceptions.RepeatedMerchandiseInStore;
import com.example.demo.model.merchandise.MerchandiseCategory;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MerchandiseInStoreTest {

    @Test
    public void whenAStoreIsNewIsDoesNotHaveMerchandise() {
        Store newStore = StoreBuilder.aStore().build();
        assertFalse(newStore.hasMerchandises());
    }

    @Test
    public void whenAStoreAddsANewMerchandiseItNowBelongsToTheStore() {
        Store store = StoreBuilder.aStore().build();
        store.addMerchandise("Fideos", "Marolio", 34.45, 23, MerchandiseCategory.GROCERY);
        assertTrue(store.sellsMerchandise("Fideos", "Marolio"));
        assertEquals(store.stockOf("Fideos", "Marolio"), 23);
    }

    @Test
    public void aStoreKnowsThePriceOfAProductItSells() {
        Store store = StoreBuilder.aStore().build();
        store.addMerchandise("Fideos", "Marolio", 34.45, 23, MerchandiseCategory.GROCERY);
        assertEquals(store.priceOf("Fideos", "Marolio"), 34.45);
    }

    @Test
    public void aStoreDoesNotKnowThePriceOfAProductItDoesNotSell() {
        Store store = StoreBuilder.aStore().build();
        assertThrows(NotFoundProductInStore.class, () -> store.priceOf("Un producto", "Que no existe"));
    }

    @Test
    public void aStoreKnowsTheStockOfAProductItSells() {
        Store store = StoreBuilder.aStore().build();
        store.addMerchandise("Fideos", "Marolio", 34.45, 23, MerchandiseCategory.GROCERY);
        assertEquals(store.stockOf("Fideos", "Marolio"), 23);
    }

    @Test
    public void aStoreDoesNotKnowTheStockOfAProductItDoesNotSell() {
        Store store = StoreBuilder.aStore().build();
        assertThrows(NotFoundProductInStore.class, () -> store.stockOf("Un producto", "Que no existe"));
    }


    @Test
    public void aStoreCannotAddTheSameProductTwice() {
        //por mismo producto se entiende mismo nombre y marca
        Store store = StoreBuilder.aStore().build();
        store.addMerchandise("Fideos", "Marolio", 34.45, 23, MerchandiseCategory.GROCERY);
        assertThrows(RepeatedMerchandiseInStore.class, () -> store.addMerchandise("Fideos", "Marolio", 34.45, 23, MerchandiseCategory.GROCERY));
    }

    @Test
    public void aStoreCanUpdateThePriceOfAnExistingProduct() {
        Store store = StoreBuilder.aStore().build();
        store.addMerchandise("Fideos", "Marolio", 34.45, 23, MerchandiseCategory.GROCERY);
        store.updatePriceFor("Fideos", "Marolio", 36.45);
        assertEquals(store.priceOf("Fideos", "Marolio"), 36.45);
    }

    @Test
    public void aStoreCanNotUpdateThePriceOfAnExistingProduct() {
        Store store = StoreBuilder.aStore().build();
        assertThrows(NotFoundProductInStore.class, () -> store.updatePriceFor("Fideos", "Marolio", 36.45));
    }

    @Test
    public void aStoreCanAddStockForAnExistingProduct() {
        Store store = StoreBuilder.aStore().build();
        store.addMerchandise("Fideos", "Marolio", 34.45, 23, MerchandiseCategory.GROCERY);
        store.addStock("Fideos", "Marolio", 20);
        assertEquals(store.stockOf("Fideos", "Marolio"), 43);
    }

    @Test
    public void aStoreCanNotAddStockForANonExistingProduct() {
        Store store = StoreBuilder.aStore().build();
        assertThrows(NotFoundProductInStore.class, () -> store.addStock("Fideos", "Marolio", 10));
    }

  /*  @Test
    public void aStoreApplyADiscountToAProduct(){
        Store store = ComercioBuilder.unComercio().build();
        MerchandiseDiscount discount = new MerchandiseDiscount(50, LocalDate.of(2020,5,1), LocalDate.of(2020, 5, 20));
        store.addMerchandise("Mayonesa", "Hellmans", 20.0, 200, MerchandiseCategory.GROCERY);
        Merchandise merchandise = store.getMerchandise("Mayonesa", "Hellmans");
        store.applyDiscountOn(merchandise, discount);
        assertTrue(merchandise.hasADiscount());
        assertEquals(50, merchandise.percentOfDiscount());
        assertEquals(10.0, merchandise.price());
    }*/

    @Test
    public void unComercioPuedeDecrementarStockParaUnProductoExistente() {
        Store store = StoreBuilder.aStore().build();
        Discount noDiscount = DiscountBuilder.aDiscount().buildNoDiscount();
        store.addMerchandise("Fideos", "Marolio", 34.45, 23, MerchandiseCategory.GROCERY);
        store.decreaseStock("Fideos", "Marolio", 20);
        assertEquals(store.stockOf("Fideos", "Marolio"), 3);
    }

    @Test
    public void unComercioNoPuedeDecrementarStockParaUnProductoInexistente() {
        Store store = StoreBuilder.aStore().build();
        assertThrows(NotFoundProductInStore.class, () -> store.decreaseStock("Fideos", "Marolio", 10));
    }

    @Test
    public void aMerchandiseInStoreOriginallyDoesNotHaveADiscount() {
        Store store = StoreBuilder.aStore().build();
        Double originalPrice = 10.0;
        store.addMerchandise("Mayonesa", "Hellmans", originalPrice, 100, MerchandiseCategory.GROCERY);
        assertEquals(store.priceOf("Mayonesa", "Hellmans"), originalPrice);
    }

}
