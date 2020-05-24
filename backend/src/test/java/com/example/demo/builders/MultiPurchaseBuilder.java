package com.example.demo.builders;

import com.example.demo.model.Purchase;
import com.example.demo.model.purchase.MultiPurchase;

import java.util.Arrays;
import java.util.List;

public class MultiPurchaseBuilder {
    
    private List<Purchase> multiPurchases = Arrays.asList(PurchaseBuilder.aPurchase().build());
    public static MultiPurchaseBuilder aMultiPurchase() {  return new MultiPurchaseBuilder();   }


    public MultiPurchaseBuilder withPurchases(List<Purchase> purchases) {
        multiPurchases = purchases;
        return this;
    }

    public MultiPurchase build() {
        return new MultiPurchase(multiPurchases);
    }
}
