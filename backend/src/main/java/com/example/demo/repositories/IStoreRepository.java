package com.example.demo.repositories;

import model.Comercio;

import java.util.List;

public interface IStoreRepository {
    List<Comercio> getStores();
    List<Comercio> getStoresWithACategory(String category);
}
