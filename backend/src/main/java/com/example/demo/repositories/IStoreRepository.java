package com.example.demo.repositories;

import com.example.demo.model.Store;
import com.example.demo.model.merchandise.Merchandise;

import java.util.List;

public interface IStoreRepository {
    List<Store> getStores();
    List<Store> getStoresWithACategory(String category);
    List<Merchandise> getProductsFrom(Store store);

    Store getStore(String storeName);

    List<Merchandise> getDiscountFromAllStores();
}
