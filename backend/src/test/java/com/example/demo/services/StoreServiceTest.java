package com.example.demo.services;

import com.example.demo.builders.StoreBuilder;
import com.example.demo.builders.DiscountBuilder;
import com.example.demo.model.discounts.Discount;
import com.example.demo.model.merchandise.MerchandiseCategory;
import com.example.demo.model.store.StoreCategory;
import com.example.demo.repositories.StoreRepository;
import com.example.demo.model.store.Store;
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
        when(storeRepositoryMock.findAll()).thenReturn(stores);

        assertEquals(stores, storeService.getStores());
    }

 /*   @Test
    public void whenWeAskStoreServiceForStoresWithACategoryItReturnsOnlyTheListOfStoresWithThatCategory() {
        List<Store> stores = StoreBuilder.storeList();
        when(storeRepositoryMock.getStoresWithACategory(StoreCategory.GROCERY)).thenReturn(stores);

        assertEquals(stores, storeService.getStoresWithACategory(StoreCategory.GROCERY));
    }
*/
  /*  @Test
    public void gettingStoreProductsList() {
        Store store = StoreBuilder.aStore().build();
        Discount noDiscount = DiscountBuilder.aDiscount().buildNoDiscount();
        store.addMerchandise("Nesquick", "Nestle", 20.4, 30, MerchandiseCategory.GROCERY, "foto nesquik+");
        when(storeRepositoryMock.findById(any())).thenReturn(java.util.Optional.of(store));

        assertEquals(storeService.getProductsFromStore(store.id()), store.listOfAvailableMerchandise());
    }
*/
    @Test
    public void addingAStoreReturnsTheStore() {
        Store store = StoreBuilder.aStore().build();
        when(storeRepositoryMock.save(any())).thenReturn(store);

        assertEquals(storeService.addStore(store), store);
    }
}
