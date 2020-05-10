package com.example.demo.model.thresholds;

public class MoneyThreshold {

    private Double moneyLimit;
    private Boolean isActive = true;

    public MoneyThreshold(Double aMoneyLimit) {
        moneyLimit = aMoneyLimit;
    }

    public Double moneyLimit() {
        return this.moneyLimit;
    }

    public Boolean isActive() {
        return this.isActive;
    }

    public void disable() {
        this.isActive = false;
    }

    public void enable() {
        this.isActive = true;
    }
}
