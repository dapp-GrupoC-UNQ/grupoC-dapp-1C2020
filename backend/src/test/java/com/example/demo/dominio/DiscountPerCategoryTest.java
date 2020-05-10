package com.example.demo.dominio;

import com.example.demo.builders.CategoryDiscountBuilder;
import com.example.demo.builders.ComercioBuilder;
import com.example.demo.builders.DiscountBuilder;
import com.example.demo.builders.MerchandiseBuilder;
import com.example.demo.model.AdquiredProduct;
import com.example.demo.model.Store;
import com.example.demo.model.discounts.CategoryDiscount;
import com.example.demo.model.merchandise.Merchandise;
import com.example.demo.model.merchandise.MerchandiseCategory;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DiscountPerCategoryTest {

    @Test
    public void anExpiredDiscountHasAPercentOfDiscountZero(){
        CategoryDiscount discount = CategoryDiscountBuilder.aDiscount().expired().build();
        assertEquals(0, discount.percentOfDiscount());
    }

    @Test
    public void aNonExpiredDiscountHasAPercentOfDiscount(){
        CategoryDiscount discount = CategoryDiscountBuilder.aDiscount().build();
        assertEquals(10, discount.percentOfDiscount());
    }

    @Test
    public void aCategoryDiscountCanBeAppliedToMerchandiseWithTheSameCategory(){
        CategoryDiscount discount = CategoryDiscountBuilder.aDiscount().withCategory(MerchandiseCategory.DAIRY_PRODUCTS).build();
        Merchandise merchandise = MerchandiseBuilder.aMerchandise().withCategory(MerchandiseCategory.DAIRY_PRODUCTS).build();
        assertTrue(discount.canApplyDiscountFor(merchandise));
    }

    @Test
    public void aCategoryDiscountCanNotBeAppliedToMerchandiseWithDiffrentCategory(){
        CategoryDiscount discount = CategoryDiscountBuilder.aDiscount().withCategory(MerchandiseCategory.DAIRY_PRODUCTS).build();
        Merchandise merchandise = MerchandiseBuilder.aMerchandise().build();
        assertFalse(discount.canApplyDiscountFor(merchandise));
    }
}
