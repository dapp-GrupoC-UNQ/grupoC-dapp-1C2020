package com.example.demo.dominio;

import com.example.demo.builders.CategoryThresholdBuilder;
import com.example.demo.builders.ComercioBuilder;
import com.example.demo.builders.PurchaseBuilder;
import com.example.demo.model.Purchase;
import com.example.demo.model.Store;
import com.example.demo.model.merchandise.MerchandiseCategory;
import org.junit.Test;

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
        Store store = ComercioBuilder.unComercio().withMerchandise("Mayonesa", "Hellmans", 15.0, 300, MerchandiseCategory.GROCERY);
        Purchase purchase = PurchaseBuilder.aPurchase().withProductOfStore("Mayonesa", "Hellmans", 4, store);
        assertTrue(categoryMoneyThreshold.breaksTheLimitWith(purchase));
    }

    @Test
    public void aPurchaseWithAHigherTotalPriceThanADisabledCategoryMoneyThresholdDoesNotBreakTheLimit() {
        CategoryMoneyThreshold categoryMoneyThreshold = CategoryThresholdBuilder.aCategoryThreshold().whichIsDisabled();
        Store store = ComercioBuilder.unComercio().withMerchandise("Mayonesa", "Hellmans", 15.0, 300, MerchandiseCategory.GROCERY);
        Purchase purchase = PurchaseBuilder.aPurchase().withProductOfStore("Mayonesa", "Hellmans", 4, store);
        assertFalse(categoryMoneyThreshold.breaksTheLimitWith(purchase));
    }

    @Test
    public void aPurchaseWithALowerTotalPriceThanAnEnabledCategoryMoneyThresholdDoesNotBreakTheLimit() {
        CategoryMoneyThreshold categoryMoneyThreshold = CategoryThresholdBuilder.aCategoryThreshold().withMoneyLimit(3000.0).withCategory(MerchandiseCategory.GROCERY).build();
        Store store = ComercioBuilder.unComercio().withMerchandise("Mayonesa", "Hellmans", 15.0, 300, MerchandiseCategory.GROCERY);
        Purchase purchase = PurchaseBuilder.aPurchase().withProductOfStore("Mayonesa", "Hellmans", 4, store);
        assertFalse(categoryMoneyThreshold.breaksTheLimitWith(purchase));
    }

    @Test
    public void aPurchaseWithALowerTotalPriceThanADisabledMoneyThresholdDoesNotBreakTheLimit() {
        CategoryMoneyThreshold categoryMoneyThreshold = CategoryThresholdBuilder.aCategoryThreshold().withMoneyLimit(3000.0).withCategory(MerchandiseCategory.GROCERY).build();
        Store store = ComercioBuilder.unComercio().withMerchandise("Mayonesa", "Hellmans", 15.0, 300, MerchandiseCategory.GROCERY);
        Purchase purchase = PurchaseBuilder.aPurchase().withProductOfStore("Mayonesa", "Hellmans", 4, store);
        assertFalse(categoryMoneyThreshold.breaksTheLimitWith(purchase));
    }

}
