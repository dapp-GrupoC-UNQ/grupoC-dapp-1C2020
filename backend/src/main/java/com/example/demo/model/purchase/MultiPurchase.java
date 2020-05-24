package com.example.demo.model.purchase;

import com.example.demo.model.Purchase;

import java.util.List;

public class MultiPurchase {

    private List<Purchase> multiPurchases;

    public MultiPurchase(List<Purchase> purchases){
        this.multiPurchases = purchases;
    }
    public Integer quantityStores() {
        return this.multiPurchases.size();
    }
}
