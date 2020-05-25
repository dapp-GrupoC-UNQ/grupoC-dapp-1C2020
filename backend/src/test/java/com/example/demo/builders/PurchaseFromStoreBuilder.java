package com.example.demo.builders;

import com.example.demo.model.*;
import com.example.demo.model.store.Store;

public class PurchaseFromStoreBuilder {
    public static PurchaseFromStoreBuilder aPurchase() {
        return new PurchaseFromStoreBuilder();
    }

    private Store purchaseStore = StoreBuilder.aStore().build();
    private User user = UserBuilder.user().build();

    public PurchaseFromStore build(){
        return new PurchaseFromStore(purchaseStore, user);
    }

    public PurchaseFromStoreBuilder withStore(Store store) {
        purchaseStore = store;
        return this;
    }

    public PurchaseFromStoreBuilder withUser(User aUser) {
        user = aUser;
        return this;
    }

    public PurchaseFromStore withProductOfStore(String producName, String productBrand, Integer quantity, Store aStore) {
        purchaseStore = aStore;
        PurchaseFromStore purchase = build();
        purchase.addProduct(producName, productBrand, quantity);
        return purchase;
    }
}