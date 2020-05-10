package com.example.demo.builders;

import com.example.demo.model.Discount;
import com.example.demo.model.NoDescount;
import com.example.demo.model.merchandise.Merchandise;
import com.example.demo.model.merchandise.MerchandiseCategory;

import java.util.Arrays;
import java.util.List;

public class MerchandiseBuilder {

    private String merchandiseName = "Fideos";
    private String merchandiseBrand = "Matarazzo";
    private Integer merchandiseStock = 9;
    private Double merchandisePrice = 65.0;
    private Discount discount = new NoDescount();
    private MerchandiseCategory category = MerchandiseCategory.NON_CLASSIFIED_PRODUCT;

    public static MerchandiseBuilder aMerchandise() {
        return new MerchandiseBuilder();
    }

    public Merchandise build() {
        return new Merchandise(merchandiseName, merchandiseBrand, merchandisePrice, merchandiseStock, category);
    }

    public static List<Merchandise> discountList() {
        Discount discount1 = DiscountBuilder.aDiscount().withPercentOfDiscount(50).build();
        Merchandise merchandise = aMerchandise().withDiscount(discount1).build();
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

    public MerchandiseBuilder withCategory(MerchandiseCategory aCategory) {
        category = aCategory;
        return this;
    }
}
