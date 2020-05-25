package com.example.demo.model.thresholds;

import com.example.demo.builders.CategoryThresholdBuilder;
import com.example.demo.builders.BillBuilder;
import com.example.demo.builders.StoreBuilder;
import com.example.demo.builders.PurchaseBuilder;
import com.example.demo.model.PurchaseFromStore;
import com.example.demo.model.purchase.Bill;
import com.example.demo.model.store.Store;
import com.example.demo.model.merchandise.MerchandiseCategory;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryMoneyThresholdTest {

    @Test
    public void aCategoryThresholdHasALimitMoneyValueForACategory() {
        Double aLimit = 3000.0;
        MerchandiseCategory category = MerchandiseCategory.BAKERY;
        CategoryMoneyThreshold categoryMoneyThreshold = CategoryThresholdBuilder.aCategoryThreshold().withMoneyLimit(aLimit).withCategory(category).build();
        assertEquals(categoryMoneyThreshold.moneyLimit(), aLimit);
        assertEquals(categoryMoneyThreshold.category(), category);
    }

    @Test
    public void aPurchaseWithAHigherTotalPriceInASpecificCategoryThanAnEnabledCategoryMoneyThresholdBreaksTheLimit() {
        CategoryMoneyThreshold categoryMoneyThreshold = CategoryThresholdBuilder.aCategoryThreshold().withMoneyLimit(30.0).withCategory(MerchandiseCategory.GROCERY).build();
        Store store = StoreBuilder.aStore().withMerchandise("Mayonesa", "Hellmans", 15.0, 300, MerchandiseCategory.GROCERY);
        PurchaseFromStore purchase = PurchaseBuilder.aPurchase().withProductOfStore("Mayonesa", "Hellmans", 4, store);
        assertTrue(categoryMoneyThreshold.breaksTheLimitWith(purchase));
    }

    @Test
    public void aPurchaseWithAHigherTotalPriceThanADisabledCategoryMoneyThresholdDoesNotBreakTheLimit() {
        CategoryMoneyThreshold categoryMoneyThreshold = CategoryThresholdBuilder.aCategoryThreshold().whichIsDisabled();
        Store store = StoreBuilder.aStore().withMerchandise("Mayonesa", "Hellmans", 15.0, 300, MerchandiseCategory.GROCERY);
        PurchaseFromStore purchase = PurchaseBuilder.aPurchase().withProductOfStore("Mayonesa", "Hellmans", 4, store);
        assertFalse(categoryMoneyThreshold.breaksTheLimitWith(purchase));
    }

    @Test
    public void aPurchaseWithALowerTotalPriceThanAnEnabledCategoryMoneyThresholdDoesNotBreakTheLimit() {
        CategoryMoneyThreshold categoryMoneyThreshold = CategoryThresholdBuilder.aCategoryThreshold().withMoneyLimit(3000.0).withCategory(MerchandiseCategory.GROCERY).build();
        Store store = StoreBuilder.aStore().withMerchandise("Mayonesa", "Hellmans", 15.0, 300, MerchandiseCategory.GROCERY);
        PurchaseFromStore purchase = PurchaseBuilder.aPurchase().withProductOfStore("Mayonesa", "Hellmans", 4, store);
        assertFalse(categoryMoneyThreshold.breaksTheLimitWith(purchase));
    }

    @Test
    public void aPurchaseWithALowerTotalPriceThanADisabledMoneyThresholdDoesNotBreakTheLimit() {
        CategoryMoneyThreshold categoryMoneyThreshold = CategoryThresholdBuilder.aCategoryThreshold().withMoneyLimit(3000.0).withCategory(MerchandiseCategory.GROCERY).build();
        Store store = StoreBuilder.aStore().withMerchandise("Mayonesa", "Hellmans", 15.0, 300, MerchandiseCategory.GROCERY);
        PurchaseFromStore purchase = PurchaseBuilder.aPurchase().withProductOfStore("Mayonesa", "Hellmans", 4, store);
        assertFalse(categoryMoneyThreshold.breaksTheLimitWith(purchase));
    }

 /*   @Test
    public void aMultiPurchaseWithAHigherTotalPriceInASpecificCategoryThanAnEnabledCategoryMoneyThresholdBreaksTheLimit() {
        CategoryMoneyThreshold categoryMoneyThreshold = CategoryThresholdBuilder.aCategoryThreshold().withMoneyLimit(30.0).withCategory(MerchandiseCategory.GROCERY).build();
        Store aStore = StoreBuilder.aStore().withMerchandise("Mayonesa", "Hellmans", 15.0, 300, MerchandiseCategory.GROCERY);
        Store anotherStore = StoreBuilder.aStore().withMerchandise("Fideos", "Marolio", 25.0, 300, MerchandiseCategory.GROCERY);
        PurchaseFromStore aPurchase = PurchaseBuilder.aPurchase().withProductOfStore("Mayonesa", "Hellmans", 2, aStore);
        PurchaseFromStore anotherPurchase = PurchaseBuilder.aPurchase().withProductOfStore("Fideos", "Marolio", 1, anotherStore);
        Bill bill = BillBuilder.aBill().withTickets(Arrays.asList(aPurchase, anotherPurchase)).build();
        assertTrue(categoryMoneyThreshold.breaksTheLimitWithMultiPurchase(bill));
    }

    @Test
    public void aMultiPurchaseWithAHigherTotalPriceThanADisabledCategoryMoneyThresholdDoesNotBreakTheLimit() {
        CategoryMoneyThreshold categoryMoneyThreshold = CategoryThresholdBuilder.aCategoryThreshold().whichIsDisabled();
        Store aStore = StoreBuilder.aStore().withMerchandise("Mayonesa", "Hellmans", 15.0, 300, MerchandiseCategory.GROCERY);
        Store anotherStore = StoreBuilder.aStore().withMerchandise("Fernet", "Branca", 400.0, 300, MerchandiseCategory.ALCOHOLIC_DRIKS);
        PurchaseFromStore aPurchase = PurchaseBuilder.aPurchase().withProductOfStore("Mayonesa", "Hellmans", 2, aStore);
        PurchaseFromStore anotherPurchase = PurchaseBuilder.aPurchase().withProductOfStore("Fernet", "Branca", 1, anotherStore);
        Bill bill = BillBuilder.aBill().withTickets(Arrays.asList(aPurchase, anotherPurchase)).build();
        assertFalse(categoryMoneyThreshold.breaksTheLimitWithMultiPurchase(bill));
    }*/

}
