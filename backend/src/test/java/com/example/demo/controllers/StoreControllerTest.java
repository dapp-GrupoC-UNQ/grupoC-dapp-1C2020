package com.example.demo.controllers;

import com.example.demo.builders.StoreAdminBuilder;
import com.example.demo.builders.StoreBuilder;
import com.example.demo.builders.DiscountBuilder;
import com.example.demo.model.discounts.Discount;
import com.example.demo.model.exceptions.NotFoundStoreException;
import com.example.demo.model.merchandise.MerchandiseCategory;
import com.example.demo.model.store.StoreCategory;
import com.example.demo.model.user.StoreAdminUser;
import com.example.demo.services.StoreService;
import com.example.demo.model.store.Store;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.junit.jupiter.api.Assertions.*;


import java.util.List;
import java.util.stream.Collectors;

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

    private List<String> storeCategoriesToString(List<StoreCategory> categories) {
        return categories.stream().map(Enum::toString).collect(Collectors.toList());
    }

    private List<String> storeOpeningDaysToString(Store store) {
        return store.storeSchedule().days().stream().map(Enum::toString).collect(Collectors.toList());
    }

    @Test
    public void ifWeAskForStoresWeGetTheActualStoresList() throws Exception {
        List<Store> stores = StoreBuilder.storeList();
        when(storeServiceMock.getStores()).thenReturn(stores);

        mockMvc.perform(get("/stores"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].storeName", is(stores.get(0).name())))
                .andExpect(jsonPath("$[1].storeName", is(stores.get(1).name())))
                .andExpect(jsonPath("$[2].storeName", is(stores.get(2).name())));
    }

    @Test
    public void ifWeAskForStoresWithACategoryWeOnlyGetTheStoresThatHaveThatCategoryList() throws Exception {
        List<Store> stores = StoreBuilder.storeWithACategoryList(StoreCategory.CLEANING_SUPPLIES);
        when(storeServiceMock.getStoresWithACategory(StoreCategory.CLEANING_SUPPLIES)).thenReturn(stores);

        mockMvc.perform(get("/stores?category=CLEANING_SUPPLIES"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].storeName", is(stores.get(0).name())));
    }

    @Test
    public void gettingStoreProductsListFromExistingStoreReturnsTheListOfProducts() throws Exception{
        Store store = StoreBuilder.aStore().withName("Coto").build();
        Discount noDiscount = DiscountBuilder.aDiscount().buildNoDiscount();
        store.addMerchandise("Pan", "Bimbo", 34.6, 12, MerchandiseCategory.BAKERY, "foto pan");
        when(storeServiceMock.getProductsFromStore(any())).thenReturn(store.listOfAvailableMerchandise());

        mockMvc.perform(get("/stores/Coto/products"))
               .andExpect(status().isOk()); //FALTA TESTEAR EL CONTENIDO
    }

    @Test
    public void gettingStoreProductsListFromNonExistingStoreReturns404() throws Exception{
        Store store = StoreBuilder.aStore().withName("Coto").build();
        Discount noDiscount = DiscountBuilder.aDiscount().buildNoDiscount();
        store.addMerchandise("Pan", "Bimbo", 34.6, 12, MerchandiseCategory.BAKERY, "foto pan");
        when(storeServiceMock.getProductsFromStore(any())).thenThrow((new NotFoundStoreException()));

        mockMvc.perform(get("/stores/Nonexistingstore/products"))
               .andExpect(status().isNotFound());
    }

    @Test
    public void addingAStoreReturnsTheStoreAdminAnd200Status() throws Exception {
        StoreAdminUser aStoreAdmin = StoreAdminBuilder.aStoreAdmin().build();
        Store aStore = aStoreAdmin.store();
        when(storeServiceMock.addStore(any())).thenReturn(aStore);

        String content = objectMapper.writeValueAsString(aStoreAdmin);
        MvcResult mvcResult = mockMvc.perform(post("/stores")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isOk())
                .andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        assertEquals(JsonPath.parse(response).read("username"), aStoreAdmin.username());
        assertEquals(JsonPath.parse(response).read("password"), aStoreAdmin.password());
        assertEquals(JsonPath.parse(response).read("store.storeName"), aStore.name());
        assertEquals(JsonPath.parse(response).read("store.storeAddress"), aStore.address());
        assertEquals(JsonPath.parse(response).read("store.storeCategories"),storeCategoriesToString(aStore.storeCategories()));
        assertEquals(JsonPath.parse(response).read("store.deliveryDistanceInKm"), aStore.deliveryDistanceInKm());
        assertEquals(JsonPath.parse(response).read("store.availablePaymentMethods"), aStore.availablePaymentMethods());
        assertEquals(JsonPath.parse(response).read("store.storeSchedule.openingTime"), aStore.storeSchedule().openingTime().toString());
        assertEquals(JsonPath.parse(response).read("store.storeSchedule.closingTime"), aStore.storeSchedule().closingTime().toString());
        assertEquals(JsonPath.parse(response).read("store.storeSchedule.openingDays"), storeOpeningDaysToString(aStore));
    }
}
