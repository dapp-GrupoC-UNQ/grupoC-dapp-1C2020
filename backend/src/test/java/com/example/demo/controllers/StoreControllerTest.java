package com.example.demo.controllers;

import com.example.demo.builders.ComercioBuilder;
import com.example.demo.builders.DiscountBuilder;
import com.example.demo.builders.MerchandiseBuilder;
import com.example.demo.model.Discount;
import com.example.demo.model.excepciones.NotFoundStoreException;
import com.example.demo.model.merchandise.Merchandise;
import com.example.demo.services.StoreService;
import com.example.demo.model.Store;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class StoreControllerTest {

    @MockBean
    StoreService storeServiceMock;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void ifWeAskForStoresWeGetTheActualStoresList() throws Exception {
        List<Store> stores = ComercioBuilder.storeList();
        when(storeServiceMock.getStores()).thenReturn(stores);

        mockMvc.perform(get("/stores"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].storeName", is(stores.get(0).name())))
                .andExpect(jsonPath("$[1].storeName", is(stores.get(1).name())));
    }

    @Test
    public void ifWeAskForStoresWithACategoryWeOnlyGetTheStoresThatHaveThatCategoryList() throws Exception {
        List<Store> stores = ComercioBuilder.storeWithACategoryList("Almacen");
        when(storeServiceMock.getStoresWithACategory("Almacen")).thenReturn(stores);

        mockMvc.perform(get("/stores?category=Almacen"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].storeName", is(stores.get(0).name())))
                .andExpect(jsonPath("$[0].storeCategory", is(stores.get(0).storeCategory())));
    }

    @Test
    public void gettingStoreProductsListFromExistingStoreReturnsTheListOfProducts() throws Exception{
        Store store = ComercioBuilder.unComercio().conNombre("Coto").build();
        Discount noDiscount = DiscountBuilder.aDiscount().buildNoDiscount();
        store.addMerchandise("Pan", "Bimbo", 34.6, 12);
        when(storeServiceMock.getProductsFromStore(any())).thenReturn(store.listOfAvailableMerchandise());

        mockMvc.perform(get("/stores/Coto/products"))
               .andExpect(status().isOk()); //FALTA TESTEAR EL CONTENIDO
    }

    @Test
    public void gettingStoreProductsListFromNonExistingStoreReturns404() throws Exception{
        Store store = ComercioBuilder.unComercio().conNombre("Coto").build();
        Discount noDiscount = DiscountBuilder.aDiscount().buildNoDiscount();
        store.addMerchandise("Pan", "Bimbo", 34.6, 12);
        when(storeServiceMock.getProductsFromStore(any())).thenThrow((new NotFoundStoreException()));

        mockMvc.perform(get("/stores/Nonexistingstore/products"))
               .andExpect(status().isNotFound());
    }

    @Test
    public void gettingStoreDiscountListFromAllStoresReturnsTheListOfDiscount() throws Exception{
        List<Merchandise> allDiscount = MerchandiseBuilder.discountList();
        when(storeServiceMock.getDiscountFromStores()).thenReturn(allDiscount);

        mockMvc.perform(get("/stores/discounts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0]discountToApply", is(allDiscount.get(0).percentOfDiscount())));
        //VER DE TESTEAR LA FECHA DE VIGENCIA
    }
}
