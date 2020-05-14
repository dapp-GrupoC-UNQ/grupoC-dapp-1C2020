package com.example.demo.model.purchase;

import com.example.demo.builders.StoreBuilder;
import com.example.demo.builders.PurchaseBuilder;
import com.example.demo.builders.UserBuilder;
import com.example.demo.model.Purchase;
import com.example.demo.model.Store;
import com.example.demo.model.User;
import com.example.demo.model.exceptions.InsufficientMerchandiseStockException;
import com.example.demo.model.exceptions.NotFoundProductInStore;
import com.example.demo.model.merchandise.MerchandiseCategory;
import org.junit.Test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

public class PurchaseTest {

    @Test
    public void aNewPurchaseHasNoProducts() {
        Purchase purchase = PurchaseBuilder.aPurchase().build();
        assertEquals(purchase.productsQuantity(), 0);
    }

    @Test
    public void aPurchaseHasAStore(){
        Store coto = StoreBuilder.aStore().build();
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
        Store storeWithProducts = StoreBuilder.aStore().build();
        storeWithProducts.addMerchandise("Mayonesa", "Hellmans", 15.8, 1, MerchandiseCategory.GROCERY, "https://i4-unilevermx.a8e.net.br/gg/mayonesa-hellmans-real-105g_170477870_7501005151931.jpg");
        Purchase purchase = PurchaseBuilder.aPurchase().withStore(storeWithProducts).build();
        assertThrows(InsufficientMerchandiseStockException.class, () -> { purchase.addProduct("Mayonesa", "Hellmans", 3); });
    }

    @Test
    public void aPurchaseWithAHigherPriceThanTheUserMoneyThresholdBreaksTheLimit() {
        Store store = StoreBuilder.withMerchandise("Mayonesa", "Hellmans", 15.0, 400, MerchandiseCategory.GROCERY);
        User userWithMoneyThreshold = UserBuilder.user().withMoneyThreshold(20.0);
        Purchase purchase = PurchaseBuilder.aPurchase().withUser(userWithMoneyThreshold).withProductOfStore("Mayonesa", "Hellmans", 10, store);
        assertTrue(purchase.breaksMoneyThreshold());
    }

    @Test
    public void aPurchaseWithALowerPriceThanTheUserMoneyThresholdDoesNotBreakTheLimit() {
        Store store = StoreBuilder.withMerchandise("Mayonesa", "Hellmans", 15.0, 400, MerchandiseCategory.GROCERY);
        User userWithMoneyThreshold = UserBuilder.user().withMoneyThreshold(20000.0);
        Purchase purchase = PurchaseBuilder.aPurchase().withUser(userWithMoneyThreshold).withProductOfStore("Mayonesa", "Hellmans", 10, store);
        assertFalse(purchase.breaksMoneyThreshold());
    }
}