package com.example.demo.builders;

import com.example.demo.model.AdquiredProduct;
import com.example.demo.model.Discount;
import com.example.demo.model.merchandise.Merchandise;

public class DiscountBuilder {

    private Merchandise merchandise;
    private Integer percentOfDiscount;

    public static DiscountBuilder aDiscount() { return new DiscountBuilder(); }

    public Discount build() {
        return new Discount(merchandise, percentOfDiscount);
    }

    public DiscountBuilder withProduct(Merchandise aMerchandise) {
        merchandise = aMerchandise;
        return this;
    }

    public DiscountBuilder withPercentOfDiscount(Integer aPercent) {
        percentOfDiscount = aPercent;
        return this;
    }
}
