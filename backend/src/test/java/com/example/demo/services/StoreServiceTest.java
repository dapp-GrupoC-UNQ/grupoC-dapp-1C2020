package com.example.demo.services;

import com.example.demo.builders.StoreBuilder;
import com.example.demo.builders.DiscountBuilder;
import com.example.demo.model.discounts.Discount;
import com.example.demo.model.merchandise.MerchandiseCategory;
import com.example.demo.repositories.StoreRepository;
import com.example.demo.model.Store;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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
        List<Store> stores = StoreBuilder.storeList();
        when(storeRepositoryMock.getStores()).thenReturn(stores);

        assertEquals(stores, storeService.getStores());
    }

    @Test
    public void whenWeAskStoreServiceForStoresWithACategoryItReturnsOnlyTheListOfStoresWithThstCategory() {
        List<Store> stores = StoreBuilder.storeList();
        when(storeRepositoryMock.getStoresWithACategory("Almacen")).thenReturn(stores);

        assertEquals(stores, storeService.getStoresWithACategory("Almacen"));
    }

    @Test
    public void gettingStoreProductsList() {
        Store store = StoreBuilder.aStore().build();
        Discount noDiscount = DiscountBuilder.aDiscount().buildNoDiscount();
        store.addMerchandise("Nesquick", "Nestle", 20.4, 30, MerchandiseCategory.GROCERY);
        when(storeRepositoryMock.getStore(any())).thenReturn(store);

        assertEquals(storeService.getProductsFromStore(store.name()), store.listOfAvailableMerchandise());
    }
}
