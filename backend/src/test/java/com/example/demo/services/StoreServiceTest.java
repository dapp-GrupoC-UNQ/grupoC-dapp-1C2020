package com.example.demo.services;

import com.example.demo.builders.MerchandiseBuilder;
import com.example.demo.builders.StoreBuilder;
import com.example.demo.builders.DiscountBuilder;
import com.example.demo.model.discounts.Discount;
import com.example.demo.model.exceptions.OptionNotAvailableForThisDeliveryType;
import com.example.demo.model.exceptions.RepeatedMerchandiseInStore;
import com.example.demo.model.merchandise.Merchandise;
import com.example.demo.model.merchandise.MerchandiseCategory;
import com.example.demo.model.store.StoreCategory;
import com.example.demo.repositories.StoreRepository;
import com.example.demo.model.store.Store;
import com.example.demo.repositories.merchandise.MerchandiseRepository;
import com.example.demo.repositories.storeSchedule.StoreScheduleRepository;
import com.example.demo.services.users.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class StoreServiceTest {

    @Mock
    StoreRepository storeRepositoryMock;

    @Mock
    StoreScheduleRepository storeScheduleRepository;

    @Mock
    MerchandiseRepository merchandiseRepository;

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
   /* @Test
    public void gettingStoreProductsList() {
        Store store = StoreBuilder.aStore().build();
        store.addMerchandise("Nesquick", "Nestle", 20.4, 30, MerchandiseCategory.GROCERY, "foto nesquik+");
        when(merchandiseRepositoryMock.findByStoreId(any())).thenReturn(store.listOfAvailableMerchandise());

        assertEquals(storeService.getProductsFromStore(store.id()), store.listOfAvailableMerchandise());
    }*/
    @Test
    public void addingAStoreReturnsTheStore() {
        Store store = StoreBuilder.aStore().build();
        when(storeScheduleRepository.save(any())).thenReturn(store.storeSchedule());
        when(storeRepositoryMock.save(any())).thenReturn(store);

        assertEquals(storeService.addStore(store), store);
    }

    @Test
    public void gettingAStoreByIdReturnsTheStore() {
        Store store = StoreBuilder.aStore().buildWithId();
        when(storeRepositoryMock.findById(any())).thenReturn(java.util.Optional.ofNullable(store));

        Store retrievedStore = storeService.getStore(store.id());
        assertEquals(retrievedStore.id(), store.id());
    }

    @Test
    public void addingAMerchandiseToAStore() {
        Store store = StoreBuilder.aStore().buildWithId();
        Merchandise merchandise = MerchandiseBuilder.aMerchandise().build();
        when(storeRepositoryMock.findById(any())).thenReturn(java.util.Optional.ofNullable(store));
        when(merchandiseRepository.save(any())).thenReturn(merchandise);

        storeService.addMerchandiseToStore(store.id(), merchandise);
        Store retrievedStore = storeService.getStore(store.id());

        assertTrue(retrievedStore.sellsMerchandise(merchandise.name(), merchandise.brand()));
    }

    @Test
    public void isNotPossibleToAddARepeatedMerchandiseToAStore() {
        Store store = StoreBuilder.aStore().buildWithId();
        Merchandise merchandise = MerchandiseBuilder.aMerchandise().build();
        when(storeRepositoryMock.findById(any())).thenReturn(java.util.Optional.ofNullable(store));
        when(merchandiseRepository.save(any())).thenReturn(merchandise);

        storeService.addMerchandiseToStore(store.id(), merchandise);

        assertThrows(RepeatedMerchandiseInStore.class , ()-> storeService.addMerchandiseToStore(store.id(), merchandise));
    }
}
