package com.example.demo.model.thresholds;

import com.example.demo.model.PurchaseFromStore;
import com.example.demo.model.purchase.Bill;
import com.example.demo.model.purchasePriceCalculator.PurchasePriceCalculator;

public class MoneyThreshold {

    private Double moneyLimit;
    private Boolean isActive = true;
    private PurchasePriceCalculator purchasePriceCalculator = new PurchasePriceCalculator();

    public MoneyThreshold(Double aMoneyLimit) {
        moneyLimit = aMoneyLimit;
    }

    public Double moneyLimit() {
        return this.moneyLimit;
    }

    public Boolean isActive() {
        return this.isActive;
    }

    public PurchasePriceCalculator purchasePriceCalculator() { return this.purchasePriceCalculator; }

    public void disable() {
        this.isActive = false;
    }

    public void enable() {
        this.isActive = true;
    }

    public Boolean breaksTheLimitWith(PurchaseFromStore purchase) {
        return this.isActive() && purchasePriceCalculator.calculatePriceFor(purchase) > this.moneyLimit();
    }

    public void updateMoneyLimit(Double newMoneyLimit) {
        this.moneyLimit = newMoneyLimit;
    }

}
