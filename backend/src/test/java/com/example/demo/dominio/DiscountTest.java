package com.example.demo.dominio;

import com.example.demo.builders.DiscountBuilder;
import com.example.demo.model.Discount;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class DiscountTest {

    @Test
    public void aDiscountOf50Percent(){
        Discount discount = DiscountBuilder.aDiscount().withPercentOfDiscount(50).build();
        assertEquals(50, discount.percentOfDiscount());
    }

    @Test
    public void aDiscountHasA1ValidDate(){
        LocalDate startDate = LocalDate.of(2020,5,2);
        LocalDate endDate = LocalDate.of(2020, 5, 10);
        Discount discount = DiscountBuilder.aDiscount().withStartDate(startDate).withEndDate(endDate).build();
        assertTrue(discount.startDate().isEqual(startDate));
        assertTrue(discount.endDate().isEqual(endDate));
    }

    @Test
    public void aDiscountIsNotAvailable(){
        LocalDate startDate = LocalDate.of(2020,5,2);
        LocalDate endDate = LocalDate.of(2020, 5, 10);
        LocalDate date = LocalDate.of(2020,5,11);
        Discount discount = DiscountBuilder.aDiscount().withStartDate(startDate).withEndDate(endDate).build();
        assertFalse(discount.isAvailableIn(date));
    }

}
