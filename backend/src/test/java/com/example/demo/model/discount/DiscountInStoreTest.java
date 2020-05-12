package com.example.demo.model.discount;

import com.example.demo.builders.StoreBuilder;
import com.example.demo.model.Store;
import com.example.demo.model.merchandise.MerchandiseCategory;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiscountInStoreTest {

    @Test
    public void aMerchandiseThatHasAMerchandiseDiscountAndItsValidHasItsPriceDecreased() {
        Store store = StoreBuilder.aStore().build();
        Double originalPrice = 10.0;
        store.addMerchandise("Mayonesa", "Hellmans", originalPrice, 100, MerchandiseCategory.GROCERY);
        store.addMerchandiseDiscountFor("Mayonesa", "Hellmans", 20, LocalDate.now().plusDays(1));
        assertEquals(store.priceOf("Mayonesa", "Hellmans"), originalPrice - (originalPrice * 20 / 100));
    }

    @Test
    public void aMerchandiseThatDoesNotHaveADiscountDoesNotGetItsPriceDecreased() {
        Store store = StoreBuilder.aStore().build();
        Double originalPrice = 10.0;
        store.addMerchandise("Mayonesa", "Hellmans", originalPrice, 100, MerchandiseCategory.GROCERY);
        assertEquals(originalPrice, store.priceOf("Mayonesa", "Hellmans"));
    }

    @Test
    public void aMerchandiseThatHasACategoryDiscountAndItsValidHasItsPriceDecreased() {
        Store store = StoreBuilder.aStore().build();
        Double originalPrice = 10.0;
        store.addMerchandise("Mayonesa", "Hellmans", originalPrice, 100, MerchandiseCategory.GROCERY);
        store.addCategoryDiscount(MerchandiseCategory.GROCERY, 20, LocalDate.now().plusDays(1));
        assertEquals(store.priceOf("Mayonesa", "Hellmans"), originalPrice - (originalPrice * 20 / 100));
    }

    @Test
    public void aMerchandiseThatDoesNotHaveACategoryDiscountDoesNotGetItsPriceDecreased() {
        Store store = StoreBuilder.aStore().build();
        Double originalPrice = 10.0;
        store.addMerchandise("Mayonesa", "Hellmans", originalPrice, 100, MerchandiseCategory.GROCERY);
        assertEquals(originalPrice, store.priceOf("Mayonesa", "Hellmans"));
    }

}
