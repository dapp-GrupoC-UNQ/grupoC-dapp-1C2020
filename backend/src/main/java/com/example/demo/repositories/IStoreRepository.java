package com.example.demo.repositories;

import com.example.demo.model.store.Store;
import com.example.demo.model.merchandise.Merchandise;
import com.example.demo.model.store.StoreCategory;

import java.util.List;

public interface IStoreRepository {
    List<Store> getStores();
    List<Store> getStoresWithACategory(StoreCategory category);
    List<Merchandise> getProductsFrom(Store store);

    Store getStore(String storeName);

    List<Merchandise> getDiscountFromAllStores();

    Store addStore(Store store);
}
