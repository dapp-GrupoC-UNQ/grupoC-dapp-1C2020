package com.example.demo.dominio;

import com.example.demo.builders.ComercioBuilder;
import com.example.demo.builders.DiscountBuilder;
import com.example.demo.builders.MerchandiseBuilder;
import com.example.demo.model.Comercio;
import com.example.demo.model.Discount;
import com.example.demo.model.merchandise.Merchandise;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DiscountTest {

    @Test
    public void aDiscountHasAMerchandise(){
        Merchandise product = MerchandiseBuilder.aMerchandise().withName("Cerveza").withBrand("Brahma").build();
        Discount discount = DiscountBuilder.aDiscount().withProduct(product).build();
        assertEquals(product.name(),discount.productName());
        assertEquals(product.brand(),discount.productBrand());
    }


    @Test
    public void aDiscountBelongAStore(){
        Discount discount = DiscountBuilder.aDiscount().build();
        Comercio store = ComercioBuilder.unComercio().build();
        store.addDiscount(discount);
        assertTrue(store.hasADiscount(discount));
    }

    @Test
    public void aDiscountOf50Percent(){
        Merchandise product = MerchandiseBuilder.aMerchandise().withPrice(40.0).build();
        Discount discount = DiscountBuilder.aDiscount().withProduct(product).withPercentOfDiscount(50).build();
        assertEquals(20.0, discount.price());
    }
}
