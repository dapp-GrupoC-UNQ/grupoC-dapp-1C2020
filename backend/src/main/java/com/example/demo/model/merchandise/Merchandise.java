package com.example.demo.model.merchandise;

import com.example.demo.model.discounts.NoDescount;
import com.example.demo.model.discounts.Discount;
import com.example.demo.model.excepciones.InvalidStockTypeException;
import com.example.demo.model.excepciones.NegativePriceMerchandiseException;
import com.example.demo.model.excepciones.InsufficientMerchandiseStockException;
import com.example.demo.model.excepciones.NegativeStockMerchandiseException;
import com.example.demo.serializers.MerchandiseJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = MerchandiseJsonSerializer.class)
public class Merchandise {

    private String merchandiseName;
    private String merchandiseBrand;
    private Double merchandisePrice;
    private Integer merchandiseStock;
    private MerchandiseCategory category;
    private Discount discountToApply = new NoDescount();

    public Merchandise(String aName, String aBrand, Double aPrice, Integer aStock, MerchandiseCategory aCategory) {
        if(aStock < 0) { throw new NegativeStockMerchandiseException();}
        if(aPrice < 0) { throw new NegativePriceMerchandiseException();}
        merchandiseName = aName;
        merchandiseBrand = aBrand;
        merchandisePrice = aPrice;
        merchandiseStock = aStock;
        category = aCategory;
    }

    public String name() {
        return this.merchandiseName;
    }

    public String brand() {
        return this.merchandiseBrand;
    }

    public Integer stock() {
        return this.merchandiseStock;
    }

    public void decreaseStock(Integer quantityToDecrease) {
        if(merchandiseStock - quantityToDecrease < 0) { throw new InsufficientMerchandiseStockException();}
        merchandiseStock -= quantityToDecrease;
    }

    public void updatePrice(Double newPrice) {
        if(newPrice < 0) { throw new NegativePriceMerchandiseException();}
        merchandisePrice = newPrice;
    }

    public Double price() {
        return this.merchandisePrice - (this.merchandisePrice * this.percentOfDiscount() / 100);
    }

    public void addStock(Integer stockToAdd) {
        if(stockToAdd < 0) { throw new InvalidStockTypeException();}
        merchandiseStock += stockToAdd;
    }

    public Boolean hasADiscount() {
        return discountToApply.hasADiscount();
    }

    public Integer percentOfDiscount() {
        return this.discountToApply.percentOfDiscount();
    }

    public void setADiscount(Discount discount) {
        this.discountToApply = discount;
    }

    public MerchandiseCategory getCategory() {
        return this.category;
    }
}
