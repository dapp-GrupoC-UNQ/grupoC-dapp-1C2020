package com.example.demo.builders;

import com.example.demo.model.discounts.MerchandiseDiscount;
import com.example.demo.model.merchandise.Merchandise;
import com.example.demo.model.merchandise.MerchandiseCategory;

import java.time.LocalDate;

public class MerchandiseDiscountBuilder {


    private Integer percentOfDiscount = 10;
    private LocalDate startDate = LocalDate.now().minusDays(1);
    private LocalDate endDate = LocalDate.now().plusDays(1);
    private Merchandise merchandise = new Merchandise("Fideos", "Matarazzo", 40.0, 100, MerchandiseCategory.GROCERY);

    public static MerchandiseDiscountBuilder aDiscount() { return new MerchandiseDiscountBuilder(); }


    public MerchandiseDiscount build() {
        return new MerchandiseDiscount(merchandise, percentOfDiscount,startDate, endDate);
    }

    public MerchandiseDiscountBuilder expired() {
        startDate = LocalDate.now().minusDays(10);
        endDate = LocalDate.now().minusDays(5);
        return this;
    }

    public MerchandiseDiscountBuilder withMerchandise(Merchandise aMerchandise) {
        merchandise = aMerchandise;
        return this;
    }
}
