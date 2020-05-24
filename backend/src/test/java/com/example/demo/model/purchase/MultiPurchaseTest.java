package com.example.demo.model.purchase;

import com.example.demo.builders.MultiPurchaseBuilder;
import com.example.demo.builders.PurchaseBuilder;
import com.example.demo.builders.StoreBuilder;
import com.example.demo.model.Purchase;
import com.example.demo.model.merchandise.MerchandiseCategory;
import com.example.demo.model.purchasePriceCalculator.PurchasePriceCalculator;
import com.example.demo.model.store.Store;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

public class MultiPurchaseTest {

    @Test
    public void aMultiPurchaseHasATwoStores(){
        Store store1 = StoreBuilder.aStore().build();
        Store store2 = StoreBuilder.aStore().build();
        Purchase purchase1 = PurchaseBuilder.aPurchase().withStore(store1).build();
        Purchase purchase2 = PurchaseBuilder.aPurchase().withStore(store2).build();
        MultiPurchase multiPurchase = MultiPurchaseBuilder.aMultiPurchase().withPurchases(Arrays.asList(purchase1, purchase2)).build();
        assertEquals(2, multiPurchase.quantityStores());
    }

    @Test
    public void aMultiPurchaseTotalIsEqualsOfSumOfAllPurchases(){
        Double aPrice = 15.8;
        Double anotherPrice = 32.5;
        Integer aQuantity = 3;
        Integer anotherQuantity = 2;
        Store storeWithProducts = StoreBuilder.withMerchandise("Mayonesa", "Hellmans", aPrice, aQuantity + 1, MerchandiseCategory.GROCERY);
        Store anotherStoreWithProducts = StoreBuilder.withMerchandise("Fideos", "Marolio", anotherPrice, anotherQuantity + 1, MerchandiseCategory.GROCERY);
        Purchase purchase1 = PurchaseBuilder.aPurchase().withProductOfStore("Mayonesa", "Hellmans", aQuantity, storeWithProducts);
        Purchase purchase2 = PurchaseBuilder.aPurchase().withProductOfStore("Fideos", "Marolio", anotherQuantity, anotherStoreWithProducts);
        MultiPurchase multiPurchase = MultiPurchaseBuilder.aMultiPurchase().withPurchases(Arrays.asList(purchase1, purchase2)).build();
        PurchasePriceCalculator calculator = new PurchasePriceCalculator();
        Double total = (aPrice * aQuantity) + (anotherPrice * anotherQuantity);
        assertEquals(total, calculator.calculatePriceForMultiPurchase(multiPurchase));
        assertEquals(aQuantity + anotherQuantity, multiPurchase.totalProductsQuantity());
    }

}
