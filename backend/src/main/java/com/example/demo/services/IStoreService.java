package com.example.demo.services;

import com.example.demo.model.Comercio;
import com.example.demo.model.merchandise.Merchandise;

import java.util.List;

public interface IStoreService {
    List<Comercio> getStores();
    public List<Comercio> getStoresWithACategory(String category);

    List<Merchandise> getProductsFromStore(String storeName);

    Comercio getStore(String storeName);
}
