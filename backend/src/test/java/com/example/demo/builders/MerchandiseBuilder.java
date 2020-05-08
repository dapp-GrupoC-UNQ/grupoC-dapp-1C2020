package com.example.demo.builders;

import com.example.demo.model.Discount;
import com.example.demo.model.Mercaderia;
import com.example.demo.model.merchandise.Merchandise;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class MerchandiseBuilder {

    private String merchandiseName = "Fideos";
    private String merchandiseBrand = "Matarazzo";
    private Integer merchandiseStock = 9;
    private Double merchandisePrice = 65.0;
    private Discount discount = new Discount(0, LocalDate.now(), LocalDate.now());

    public static MerchandiseBuilder aMerchandise() {
        return new MerchandiseBuilder();
    }

    public Merchandise build() {
        return new Merchandise(merchandiseName, merchandiseBrand, merchandisePrice, merchandiseStock);
    }

    public static List<Merchandise> discountList() {
        Discount discount1 = DiscountBuilder.aDiscount().withPercentOfDiscount(50).build();
        Merchandise merchandise = aMerchandise().build();
        merchandise.setADiscount(discount1);
        return Arrays.asList(merchandise);
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

    public MerchandiseBuilder withDiscount(Discount aDiscount) {
        discount = aDiscount;
        return this;
    }
}
