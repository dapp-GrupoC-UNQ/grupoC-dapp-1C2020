package com.example.demo.model;

import com.example.demo.model.store.Store;

import java.util.ArrayList;
import java.util.List;

public class PurchaseFromStore {

    private Store purchaseStore;
    private User purchaseUser;
    private List<AcquiredProduct> productList = new ArrayList<>();

    public PurchaseFromStore(Store store, User name){
        purchaseStore = store;
        purchaseUser = name;
    }

    public Store store() { return this.purchaseStore; }

    public User user() { return this.purchaseUser;  }

    public Integer productsQuantity() { return this.productList.stream().mapToInt(AcquiredProduct::quantity).sum();  }

    public List<AcquiredProduct> getListOfAdquiredProducts() {
        return this.productList;
    }

    public void addProduct(String productName, String productBrand, Integer quantity) {
        this.productList.add(this.store().getProduct(productName, productBrand, quantity));
    }
}