package com.example.demo.builders;

import com.example.demo.model.Mercaderia;
import com.example.demo.model.merchandise.Merchandise;

public class MerchandiseBuilder {

    private String merchandiseName = "Fideos";
    private String merchandiseBrand = "Matarazzo";
    private Integer merchandiseStock = 9;
    private Double merchandisePrice = 65.0;

    public static MerchandiseBuilder aMerchandise() {
        return new MerchandiseBuilder();
    }

    public Merchandise build() {
        return new Merchandise(merchandiseName, merchandiseBrand, merchandisePrice, merchandiseStock);
    }

    public MerchandiseBuilder withName(String aName) {
        merchandiseName = aName;
        return this;
    }

    public MerchandiseBuilder withBrand(String aBrand) {
        merchandiseBrand = aBrand;
        return this;
    }

    public MerchandiseBuilder withStock(Integer aStock) {
        merchandiseStock = aStock;
        return this;
    }

    public MerchandiseBuilder withPrice(Double aPrice) {
        merchandisePrice = aPrice;
        return this;
    }
}
