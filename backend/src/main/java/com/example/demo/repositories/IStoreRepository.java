package com.example.demo.repositories;

import com.example.demo.model.Comercio;
import com.example.demo.model.merchandise.Merchandise;

import java.util.List;

public interface IStoreRepository {
    List<Comercio> getStores();
    List<Comercio> getStoresWithACategory(String category);
    List<Merchandise> getProductsFrom(Comercio store);

    Comercio getStore(String storeName);

    List<Merchandise> getDiscountFromAllStores();
}
