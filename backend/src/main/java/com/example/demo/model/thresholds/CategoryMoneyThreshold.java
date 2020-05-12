package com.example.demo.model.thresholds;

import com.example.demo.model.Purchase;
import com.example.demo.model.merchandise.MerchandiseCategory;

public class CategoryMoneyThreshold extends MoneyThreshold {

    private MerchandiseCategory category;

    public CategoryMoneyThreshold(Double moneyLimit, MerchandiseCategory aCategory) {
        super(moneyLimit);
        category = aCategory;
    }

    public MerchandiseCategory category() {
        return this.category;
    }

    @Override
    public Boolean breaksTheLimitWith(Purchase purchase) {
        return this.isActive() && this.purchasePriceCalculator().calculatePriceForCategory(purchase, this.category()) > this.moneyLimit();
    }
}
