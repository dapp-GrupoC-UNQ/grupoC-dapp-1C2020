package com.example.demo.services;

import com.example.demo.model.store.Store;
import com.example.demo.model.merchandise.Merchandise;
import com.example.demo.model.store.StoreCategory;

import java.util.List;

public interface IStoreService {
    List<Store> getStores();
    List<Store> getStoresWithACategory(StoreCategory category);

    List<Merchandise> getProductsFromStore(String storeName);

    Store getStore(String storeName);

    List<Merchandise> getDiscountFromStores();

    Store addStore(Store store);
}
