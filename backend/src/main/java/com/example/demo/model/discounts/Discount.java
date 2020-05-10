package com.example.demo.model.discounts;

import java.time.LocalDate;

public interface Discount {

    public Boolean hasADiscount();

    public Integer percentOfDiscount();

    public Boolean isAvailableIn(LocalDate date);

}
