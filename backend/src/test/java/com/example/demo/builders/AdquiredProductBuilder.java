package com.example.demo.builders;

import com.example.demo.model.AdquiredProduct;

public class AdquiredProductBuilder {

    private String productName;
    private String productBrand;
    private Double productPrice;
    private Integer productQuantity;

    public static AdquiredProductBuilder aProduct(){ return new AdquiredProductBuilder(); }

    public AdquiredProduct build() {
        return new AdquiredProduct(productName, productBrand, productPrice, productQuantity);
    }


    public AdquiredProductBuilder withName(String name) {
        productName =name;
        return this;
    }

    public AdquiredProductBuilder withBrand(String brand) {
        productBrand = brand;
        return this;
    }

    public AdquiredProductBuilder withPrice(Double price) {
        productPrice = price;
        return this;
    }
}
