package com.example.demo.model;

import com.example.demo.model.merchandise.Merchandise;

public class Discount {

    private Merchandise merchandise;

    public Discount(Merchandise aMerchandise){
        this.merchandise = aMerchandise;
    }


    public String productName() {
        return this.merchandise.name();
    }

    public String productBrand() {
        return this.merchandise.brand();
    }
}
