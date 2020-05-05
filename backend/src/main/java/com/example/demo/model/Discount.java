package com.example.demo.model;

import com.example.demo.model.merchandise.Merchandise;
import org.apache.tomcat.jni.Local;

import java.time.LocalDate;

public class Discount {

    private Merchandise merchandise;
    private Integer percentOfDiscount;
    private LocalDate startDate;
    private LocalDate endDate;

    public Discount(Merchandise aMerchandise, Integer aPercent, LocalDate startDate, LocalDate endDate){
        this.merchandise = aMerchandise;
        this.percentOfDiscount = aPercent;
        this.startDate = startDate;
        this.endDate = endDate;
    }


    public String productName() {
        return this.merchandise.name();
    }

    public String productBrand() {
        return this.merchandise.brand();
    }

    public Double price() {
        return this.merchandise.price() * percentOfDiscount / 100;
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

    public Merchandise merchadise() {
        return this.merchandise;
    }
}
