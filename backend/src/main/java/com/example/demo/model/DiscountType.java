package com.example.demo.model;

import java.time.LocalDate;

public interface DiscountType {

    public Boolean hasADiscount();

    public Integer percentOfDiscount();

    public LocalDate startDate();

    public LocalDate endDate();

    public Boolean isAvailableIn(LocalDate date);

}
