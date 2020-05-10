package com.example.demo.model.purchasePriceCalculator;

import com.example.demo.model.AdquiredProduct;
import com.example.demo.model.Purchase;

public class PurchasePriceCalculator {

    public Double calculatePriceFor(Purchase purchase) {
        return purchase.getListOfAdquiredProducts().stream().mapToDouble(AdquiredProduct::totalPrice).sum();
    }
}
