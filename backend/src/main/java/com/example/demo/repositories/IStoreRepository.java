package com.example.demo.repositories;

import com.example.demo.model.Comercio;

import java.util.List;

public interface IStoreRepository {
    List<Comercio> getStores();
    List<Comercio> getStoresWithACategory(String category);
}
