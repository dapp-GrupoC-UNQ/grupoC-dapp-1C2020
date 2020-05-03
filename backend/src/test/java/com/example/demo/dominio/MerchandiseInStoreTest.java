package com.example.demo.dominio;

import com.example.demo.builders.ComercioBuilder;
import com.example.demo.model.Comercio;
import com.example.demo.model.excepciones.NotFoundProductInStore;
import com.example.demo.model.excepciones.RepeatedMerchandiseInStore;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MerchandiseInStoreTest {

    @Test
    public void whenAStoreIsNewIsDoesNotHaveMerchandise() {
        Comercio comercioNuevo = ComercioBuilder.unComercio().build();
        assertFalse(comercioNuevo.hasMerchandises());
    }

    @Test
    public void whenAStoreAddsANewMerchandiseItNowBelongsToTheStore() {
        Comercio comercio = ComercioBuilder.unComercio().build();
        comercio.addMerchandise("Fideos", "Marolio", 34.45, 23);
        assertTrue(comercio.sellsMerchandise("Fideos", "Marolio"));
        assertEquals(comercio.stockOf("Fideos", "Marolio"), 23);
    }

    @Test
    public void aStoreKnowsThePriceOfAProductItSells() {
        Comercio comercio = ComercioBuilder.unComercio().build();
        comercio.addMerchandise("Fideos", "Marolio", 34.45, 23);
        assertEquals(comercio.priceOf("Fideos", "Marolio"), 34.45);
    }

    @Test
    public void aStoreDoesNotKnowThePriceOfAProductItDoesNotSell() {
        Comercio comercio = ComercioBuilder.unComercio().build();
        assertThrows(NotFoundProductInStore.class, () -> comercio.priceOf("Un producto", "Que no existe"));
    }

    @Test
    public void aStoreKnowsTheStockOfAProductItSells() {
        Comercio comercio = ComercioBuilder.unComercio().build();
        comercio.addMerchandise("Fideos", "Marolio", 34.45, 23);
        assertEquals(comercio.stockOf("Fideos", "Marolio"), 23);
    }

    @Test
    public void aStoreDoesNotKnowTheStockOfAProductItDoesNotSell() {
        Comercio comercio = ComercioBuilder.unComercio().build();
        assertThrows(NotFoundProductInStore.class, () -> comercio.stockOf("Un producto", "Que no existe"));
    }


    @Test
    public void aStoreCannotAddTheSameProductTwice() {
        //por mismo producto se entiende mismo nombre y marca
        Comercio comercio = ComercioBuilder.unComercio().build();
        comercio.addMerchandise("Fideos", "Marolio", 34.45, 23);
        assertThrows(RepeatedMerchandiseInStore.class, () -> comercio.addMerchandise("Fideos", "Marolio", 34.45, 23));
    }

    @Test
    public void aStoreCanUpdateThePriceOfAnExistingProduct() {
        Comercio comercio = ComercioBuilder.unComercio().build();
        comercio.addMerchandise("Fideos", "Marolio", 34.45, 23);
        comercio.updatePriceFor("Fideos", "Marolio", 36.45);
        assertEquals(comercio.priceOf("Fideos", "Marolio"), 36.45);
    }

    @Test
    public void aStoreCanNotUpdateThePriceOfAnExistingProduct() {
        Comercio comercio = ComercioBuilder.unComercio().build();
        assertThrows(NotFoundProductInStore.class, () -> comercio.updatePriceFor("Fideos", "Marolio", 36.45));
    }

    @Test
    public void aStoreCanAddStockForAnExistingProduct() {
        Comercio comercio = ComercioBuilder.unComercio().build();
        comercio.addMerchandise("Fideos", "Marolio", 34.45, 23);
        comercio.addStock("Fideos", "Marolio", 20);
        assertEquals(comercio.stockOf("Fideos", "Marolio"), 43);
    }

    @Test
    public void aStoreCanNotAddStockForANonExistingProduct() {
        Comercio comercio = ComercioBuilder.unComercio().build();
        assertThrows(NotFoundProductInStore.class, () -> comercio.addStock("Fideos", "Marolio", 10));
    }

    @Test
    public void unComercioPuedeDecrementarStockParaUnProductoExistente() {
        Comercio comercio = ComercioBuilder.unComercio().build();
        comercio.addMerchandise("Fideos", "Marolio", 34.45, 23);
        comercio.decreaseStock("Fideos", "Marolio", 20);
        assertEquals(comercio.stockOf("Fideos", "Marolio"), 3);
    }

    @Test
    public void unComercioNoPuedeDecrementarStockParaUnProductoInexistente() {
        Comercio comercio = ComercioBuilder.unComercio().build();
        assertThrows(NotFoundProductInStore.class, () -> comercio.decreaseStock("Fideos", "Marolio", 10));
    }
}
