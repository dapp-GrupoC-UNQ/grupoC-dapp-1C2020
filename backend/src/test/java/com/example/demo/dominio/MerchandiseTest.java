package com.example.demo.dominio;

import com.example.demo.builders.DiscountBuilder;
import com.example.demo.builders.MerchandiseBuilder;
import com.example.demo.model.Discount;
import com.example.demo.model.excepciones.InvalidStockTypeException;
import com.example.demo.model.excepciones.NegativePriceMerchandiseException;
import com.example.demo.model.excepciones.InsufficientMerchandiseStockException;
import com.example.demo.model.excepciones.NegativeStockMerchandiseException;
import com.example.demo.model.merchandise.Merchandise;
import org.junit.Test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

public class MerchandiseTest {

    @Test
    public void aMerchandiseHasAName() {
        Merchandise merchandise = MerchandiseBuilder.aMerchandise().withName("Bread").build();
        assertEquals("Bread", merchandise.name());
    }

    @Test
    public void aMerchandiseHasABrand() {
        Merchandise merchandise = MerchandiseBuilder.aMerchandise().withBrand("Bimbo").build();
        assertEquals("Bimbo", merchandise.brand());
    }

    @Test
    public void aMerchandiseCannotHaveNegativeStock() {
        assertThrows(NegativeStockMerchandiseException.class,
                () -> MerchandiseBuilder.aMerchandise().withStock(-4).build());
    }

    @Test
    public void aMerchandiseCannotHaveNegativePrice() {
        assertThrows(NegativePriceMerchandiseException.class,
                () -> MerchandiseBuilder.aMerchandise().withPrice(-4.7).build());
    }

    @Test
    public void ifThereIsAvailableStockThenThisOneCanBeDecremented() {
        Merchandise merchandise = MerchandiseBuilder.aMerchandise().withStock(10).build();
        merchandise.decreaseStock(2);
        assertEquals(8, merchandise.stock());
    }

    @Test
    public void youCannotDecreaseTheStockInABiggerNumberThanItself() {
        Merchandise merchandise = MerchandiseBuilder.aMerchandise().withStock(10).build();
        assertThrows(InsufficientMerchandiseStockException.class,
                () -> merchandise.decreaseStock(30));
    }

    @Test
    public void aMerchandiseCanHaveItsPriceUpdatedIfTheNewPriceIsAPositiveNumber() {
        Merchandise merchandise = MerchandiseBuilder.aMerchandise().withPrice(10.0).build();
        merchandise.updatePrice(22.34);
        assertEquals(22.34, merchandise.price());
    }

    @Test
    public void aMerchandiseCanNotHaveItsPriceUpdatedIfTheNewPriceIsANegativeNumber() {
        Merchandise merchandise = MerchandiseBuilder.aMerchandise().withPrice(10.0).build();
        assertThrows(NegativePriceMerchandiseException.class,
                () -> merchandise.updatePrice(-22.34));
    }

    @Test
    public void aMerchandiseStockCanBeIncreasedIfTheStockToAddIsAPositiveInteger() {
        Merchandise merchandise = MerchandiseBuilder.aMerchandise().withStock(10).build();
        merchandise.addStock(2);
        assertEquals(12, merchandise.stock());
    }

    @Test
    public void aMerchandiseStockCanNotBeIncreasedIfTheStockToAddIsANegativeInteger() {
        Merchandise merchandise = MerchandiseBuilder.aMerchandise().withStock(10).build();
        assertThrows(InvalidStockTypeException.class,
                () -> merchandise.addStock(-2));
    }
}
