package com.example.demo.model.purchase;

import com.example.demo.model.Purchase;

import java.util.List;

public class MultiPurchase {

    private List<Purchase> allPurchases;

    public MultiPurchase(List<Purchase> purchases){
        this.allPurchases = purchases;
    }
    public Integer quantityStores() {
        return this.allPurchases.size();
    }

    public List<Purchase> getPurchases() {
        return this.allPurchases;
    }

    public Integer totalProductsQuantity() {
        return allPurchases.stream().mapToInt(Purchase::productsQuantity).sum();
    }

}
