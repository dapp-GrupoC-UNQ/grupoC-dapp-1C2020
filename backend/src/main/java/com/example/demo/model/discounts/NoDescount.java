package com.example.demo.model.discounts;

import com.example.demo.model.discounts.Discount;

import java.time.LocalDate;

public class NoDescount implements Discount {
    @Override
    public Boolean hasADiscount() {
        return false;
    }

    @Override
    public Integer percentOfDiscount() {
        return 0;
    }

    @Override
    public Boolean isAvailableIn(LocalDate date) {
        return false;
    }
}
