package com.example.demo.services;

import com.example.demo.model.Store;
import com.example.demo.model.merchandise.Merchandise;

import java.util.List;

public interface IStoreService {
    List<Store> getStores();
    List<Store> getStoresWithACategory(String category);

    List<Merchandise> getProductsFromStore(String storeName);

    Store getStore(String storeName);

    List<Merchandise> getDiscountFromStores();
}
