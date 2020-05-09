package com.example.demo.model;

import java.time.LocalDate;

public class NoDescount implements DiscountType {
    @Override
    public Boolean hasADiscount() {
        return false;
    }

    @Override
    public Integer percentOfDiscount() {
        return 0;
    }

    @Override
    public LocalDate startDate() {
        return null; // VER
    }

    @Override
    public LocalDate endDate() {
        return null; //VER
    }

    @Override
    public Boolean isAvailableIn(LocalDate date) {
        return false;
    }
}
