package com.example.demo.services;

import com.example.demo.builders.ComercioBuilder;
import com.example.demo.repositories.StoreRepository;
import com.example.demo.model.Comercio;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class StoreServiceTest {

    @Mock
    StoreRepository storeRepositoryMock;

    @InjectMocks
    StoreService storeService;

    @Test
    public void whenWeAskStoreServiceForStoresItReturnsTheListOfActualStores() {
        List<Comercio> stores = ComercioBuilder.storeList();
        when(storeRepositoryMock.getStores()).thenReturn(stores);

        assertEquals(stores, storeService.getStores());
    }

    @Test
    public void whenWeAskStoreServiceForStoresWithACategoryItReturnsOnlyTheListOfStoresWithThstCategory() {
        List<Comercio> stores = ComercioBuilder.storeList();
        when(storeRepositoryMock.getStoresWithACategory("Almacen")).thenReturn(stores);

        assertEquals(stores, storeService.getStoresWithACategory("Almacen"));
    }
}
