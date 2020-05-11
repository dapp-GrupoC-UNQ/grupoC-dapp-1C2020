package com.example.demo.dominio;

import com.example.demo.builders.ComercioBuilder;
import com.example.demo.model.Store;
import com.example.demo.model.merchandise.MerchandiseCategory;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiscountInStoreTest {

    @Test
    public void aMerchandiseThatHasAMerchandiseDiscountAndItsValidHasItsPriceDecreased() {
        Store store = ComercioBuilder.unComercio().build();
        Double originalPrice = 10.0;
        store.addMerchandise("Mayonesa", "Hellmans", originalPrice, 100, MerchandiseCategory.GROCERY);
        store.addMerchandiseDiscountFor("Mayonesa", "Hellmans", 20, LocalDate.now().plusDays(1));
        assertEquals(store.priceOf("Mayonesa", "Hellmans"), originalPrice - (originalPrice * 20 / 100));
    }

    @Test
    public void aMerchandiseThatDoesNotHaveADiscountDoesNotGetItsPriceDecreased() {
        Store store = ComercioBuilder.unComercio().build();
        Double originalPrice = 10.0;
        store.addMerchandise("Mayonesa", "Hellmans", originalPrice, 100, MerchandiseCategory.GROCERY);
        assertEquals(originalPrice, store.priceOf("Mayonesa", "Hellmans"));
    }

    @Test
    public void aMerchandiseThatHasACategoryDiscountAndItsValidHasItsPriceDecreased() {
        Store store = ComercioBuilder.unComercio().build();
        Double originalPrice = 10.0;
        store.addMerchandise("Mayonesa", "Hellmans", originalPrice, 100, MerchandiseCategory.GROCERY);
        store.addCategoryDiscount(MerchandiseCategory.GROCERY, 20, LocalDate.now().plusDays(1));
        assertEquals(store.priceOf("Mayonesa", "Hellmans"), originalPrice - (originalPrice * 20 / 100));
    }

    @Test
    public void aMerchandiseThatDoesNotHaveACategoryDiscountDoesNotGetItsPriceDecreased() {
        Store store = ComercioBuilder.unComercio().build();
        Double originalPrice = 10.0;
        store.addMerchandise("Mayonesa", "Hellmans", originalPrice, 100, MerchandiseCategory.GROCERY);
        assertEquals(originalPrice, store.priceOf("Mayonesa", "Hellmans"));
    }

}
