package com.example.demo.builders;

import com.example.demo.model.Discount;
import com.example.demo.model.DiscountType;
import com.example.demo.model.NoDescount;

import java.time.LocalDate;

public class DiscountBuilder {

    private Integer percentOfDiscount;
    private LocalDate startDate;
    private LocalDate endDate;

    public static DiscountBuilder aDiscount() { return new DiscountBuilder(); }


    public DiscountType build() {
        return new Discount(percentOfDiscount,startDate, endDate);
    }

    public DiscountType buildNoDiscount() {  return new NoDescount();  }

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
