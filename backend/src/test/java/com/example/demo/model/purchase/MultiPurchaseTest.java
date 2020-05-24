package com.example.demo.model.purchase;

import com.example.demo.builders.MultiPurchaseBuilder;
import com.example.demo.builders.PurchaseBuilder;
import com.example.demo.builders.StoreBuilder;
import com.example.demo.model.Purchase;
import com.example.demo.model.store.Store;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

public class MultiPurchaseTest {

    @Test
    public void unaCompraSucedeEnDosTiendas(){
        Store store1 = StoreBuilder.aStore().withName("Coto").build();
        Store store2 = StoreBuilder.aStore().withName("Lo de tito").build();
        Purchase purchase1 = PurchaseBuilder.aPurchase().withStore(store1).build();
        Purchase purchase2 = PurchaseBuilder.aPurchase().withStore(store2).build();
        MultiPurchase multiPurchase = MultiPurchaseBuilder.aMultiPurchase().withPurchases(Arrays.asList(purchase1, purchase2)).build();
        assertEquals(2, multiPurchase.quantityStores());
    }

}
