package com.example.demo.model;

import com.example.demo.serializers.DiscountJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDate;

@JsonSerialize(using = DiscountJsonSerializer.class)
public class PercentageDiscount implements Discount {

    private Integer percentOfDiscount;
    private LocalDate startDate;
    private LocalDate endDate;

    public PercentageDiscount(Integer aPercent, LocalDate startDate, LocalDate endDate){
        this.percentOfDiscount = aPercent;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDate startDate() {
        return this.startDate;
    }

    public LocalDate endDate() {
        return this.endDate;
    }

    public Boolean isAvailableIn(LocalDate date) {
        return startDate.isBefore(date) && endDate.isAfter(date);
    }

    public Integer percentOfDiscount() {
        if(isAvailableIn(LocalDate.now())){
            return this.percentOfDiscount;
        }
        return 0;
    }

    @Override
    public Boolean hasADiscount() {
        return true;
    }
}
