package com.example.demo.controllers;

import builders.ComercioBuilder;
import com.example.demo.services.StoreService;
import model.Comercio;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

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
        List<Comercio> stores = ComercioBuilder.storeList();
        when(storeServiceMock.getStores()).thenReturn(stores);

        MvcResult response = mockMvc.perform(get("/stores")).andReturn();
    }
}
