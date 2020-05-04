package com.example.demo.model;

import com.example.demo.model.merchandise.Merchandise;

public class Discount {

    private Merchandise merchandise;
    private Integer percentOfDiscount;

    public Discount(Merchandise aMerchandise, Integer aPercent){
        this.merchandise = aMerchandise;
        this.percentOfDiscount = aPercent;
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
}
