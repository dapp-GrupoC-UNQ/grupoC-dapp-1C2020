package com.example.demo.model;

public class NoDescount implements DiscountType {
    @Override
    public Boolean hasADiscount() {
        return false;
    }
}
