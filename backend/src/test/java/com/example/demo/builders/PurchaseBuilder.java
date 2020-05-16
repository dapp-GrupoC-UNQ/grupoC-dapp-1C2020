package com.example.demo.builders;

import com.example.demo.model.*;
import com.example.demo.model.store.Store;

public class PurchaseBuilder {
    public static PurchaseBuilder aPurchase() {
        return new PurchaseBuilder();
    }

    private Store purchaseStore = StoreBuilder.aStore().build();
    private User user = UserBuilder.user().build();

    public Purchase build(){
        return new Purchase(purchaseStore, user);
    }

    public PurchaseBuilder withStore(Store store) {
        purchaseStore = store;
        return this;
    }

    public PurchaseBuilder withUser(User aUser) {
        user = aUser;
        return this;
    }

    public Purchase withProductOfStore(String producName, String productBrand, Integer quantity, Store aStore) {
        purchaseStore = aStore;
        Purchase purchase = build();
        purchase.addProduct(producName, productBrand, quantity);
        return purchase;
    }
}