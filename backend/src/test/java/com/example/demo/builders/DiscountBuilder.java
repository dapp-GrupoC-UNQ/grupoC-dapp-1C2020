package com.example.demo.builders;

import com.example.demo.model.Discount;
import com.example.demo.model.merchandise.Merchandise;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class DiscountBuilder {

    private Integer percentOfDiscount;
    private LocalDate startDate;
    private LocalDate endDate;

    public static DiscountBuilder aDiscount() { return new DiscountBuilder(); }

    public static List<Discount> discountList() {
        Discount discount1 = aDiscount().withPercentOfDiscount(50).build();
        return Arrays.asList(discount1);
    }

    public Discount build() {
        return new Discount(percentOfDiscount,startDate, endDate);
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
