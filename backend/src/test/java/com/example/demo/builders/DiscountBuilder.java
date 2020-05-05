package com.example.demo.builders;

import com.example.demo.model.AdquiredProduct;
import com.example.demo.model.Discount;
import com.example.demo.model.merchandise.Merchandise;

import java.time.LocalDate;

public class DiscountBuilder {

    private Merchandise merchandise;
    private Integer percentOfDiscount;
    private LocalDate startDate;
    private LocalDate endDate;

    public static DiscountBuilder aDiscount() { return new DiscountBuilder(); }

    public Discount build() {
        return new Discount(merchandise, percentOfDiscount,startDate, endDate);
    }

    public DiscountBuilder withProduct(Merchandise aMerchandise) {
        merchandise = aMerchandise;
        return this;
    }

    public DiscountBuilder withPercentOfDiscount(Integer aPercent) {
        percentOfDiscount = aPercent;
        return this;
    }

    public DiscountBuilder withStartDate(LocalDate date) {
        startDate = date;
        return this;
    }

    public DiscountBuilder withEndDate(LocalDate date) {
        endDate =date;
        return this;
    }
}
