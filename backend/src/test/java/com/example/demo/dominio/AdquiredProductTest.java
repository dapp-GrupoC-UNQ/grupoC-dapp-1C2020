package com.example.demo.dominio;

import com.example.demo.builders.AdquiredProductBuilder;
import com.example.demo.model.AdquiredProduct;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AdquiredProductTest {

    @Test
    public void anAdquiredProductHasAName(){
        AdquiredProduct product = AdquiredProductBuilder.aProduct().withName("Fideos").build();
        assertEquals("Fideos", product.name());
    }

    @Test
    public void anAdquiredProductHasABrand(){
        AdquiredProduct product = AdquiredProductBuilder.aProduct().withBrand("Knorr").build();
        assertEquals("Knorr", product.brand());
    }

    @Test
    public void anAdquiredProductHasAPrice(){
        AdquiredProduct product = AdquiredProductBuilder.aProduct().withPrice(40.2).build();
        assertEquals(40.2, product.price());
    }

    @Test
    public void anAdquiredProductHasAQuantity(){
        AdquiredProduct product = AdquiredProductBuilder.aProduct().withQuantity(4).build();
        assertEquals(4, product.quantity());
    }
}
