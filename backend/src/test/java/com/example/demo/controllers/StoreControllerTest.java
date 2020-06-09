package com.example.demo.controllers;

import com.example.demo.builders.StoreBuilder;
import com.example.demo.model.exceptions.NotFoundStoreException;
import com.example.demo.model.merchandise.MerchandiseCategory;
import com.example.demo.model.store.StoreCategory;
import com.example.demo.services.StoreService;
import com.example.demo.model.store.Store;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import java.util.Random;

import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class StoreControllerTest {

    @MockBean
    StoreService storeServiceMock;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void ifWeAskForStoresWeGetTheActualStoresList() throws Exception {
        List<Store> stores = StoreBuilder.storeList();
        when(storeServiceMock.getStores()).thenReturn(applyIdToStores(stores));

        mockMvc.perform(get("/stores"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(stores.get(0).id())))
                .andExpect(jsonPath("$[1].id", is(stores.get(1).id())))
                .andExpect(jsonPath("$[2].id", is(stores.get(2).id())))
                .andExpect(jsonPath("$[0].storeName", is(stores.get(0).name())))
                .andExpect(jsonPath("$[1].storeName", is(stores.get(1).name())))
                .andExpect(jsonPath("$[2].storeName", is(stores.get(2).name())));
    }

    @Test
    public void ifWeAskForStoresWithACategoryWeOnlyGetTheStoresThatHaveThatCategoryList() throws Exception {
        List<Store> stores = StoreBuilder.storeWithACategoryList(StoreCategory.CLEANING_SUPPLIES);
        when(storeServiceMock.getStoresWithACategory(StoreCategory.CLEANING_SUPPLIES)).thenReturn(applyIdToStores(stores));

        mockMvc.perform(get("/stores?category=CLEANING_SUPPLIES"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].storeName", is(stores.get(0).name())));
    }

    @Test
    public void gettingStoreProductsListFromExistingStoreReturnsTheListOfProducts() throws Exception{
        Store store = StoreBuilder.aStore().withName("Coto").build();
        store.addMerchandise("Pan", "Bimbo", 34.6, 12, MerchandiseCategory.BAKERY, "foto pan");
        store.setId(new Random().nextLong()); //que onda esto?
        when(storeServiceMock.getProductsFromStore(any())).thenReturn(store.listOfAvailableMerchandise());

        mockMvc.perform(get("/stores/" + (store.id()).toString() + "/products"))
               .andExpect(status().isOk()); //FALTA TESTEAR EL CONTENIDO
    }

    @Test
    public void gettingStoreProductsListFromNonExistingStoreReturns404() throws Exception{
        when(storeServiceMock.getProductsFromStore(any())).thenThrow((new NotFoundStoreException()));
        Long nonExistingId = (new Random().nextLong());
        mockMvc.perform(get("/stores/"+ nonExistingId.toString() +"/products"))
               .andExpect(status().isNotFound());
    }

    private List<Store> applyIdToStores(List<Store> stores) {
        stores.stream().forEach(store -> store.setId(new Random().nextLong()));
        return stores;
    }
}
