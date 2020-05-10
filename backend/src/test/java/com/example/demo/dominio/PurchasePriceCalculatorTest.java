package com.example.demo.dominio;

import com.example.demo.builders.PurchaseBuilder;
import com.example.demo.model.Purchase;
import com.example.demo.model.purchasePriceCalculator.PurchasePriceCalculator;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PurchasePriceCalculatorTest {

    @Test
    public void priceForAPurchaseWithNoProductsIsZero() {
        Purchase purchase = PurchaseBuilder.aPurchase().build();
        PurchasePriceCalculator calculator = new PurchasePriceCalculator();
        assertEquals(calculator.calculatePriceFor(purchase), 0);
    }

/*    @Test
    public void aPurchaseTotalIsEqualToTheSumOfAllItsProducts(){
        Double aPrice = 15.8;
        Integer aQuantity = 3;
        Store storeWithProducts = ComercioBuilder.unComercio().build();
        storeWithProducts.addMerchandise("Mayonesa", "Hellmans", aPrice, aQuantity + 1);
        Purchase purchase = PurchaseBuilder.aPurchase().withStore(storeWithProducts).build();
        purchase.addProduct("Mayonesa", "Hellmans", aQuantity);
        assertEquals(aPrice * aQuantity, purchase.total());
        assertEquals(aQuantity, purchase.productsQuantity());
    }*/
}
