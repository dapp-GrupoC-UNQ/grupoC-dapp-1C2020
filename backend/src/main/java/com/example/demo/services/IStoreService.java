package com.example.demo.services;

import com.example.demo.model.Comercio;

import java.util.List;

public interface IStoreService {
    List<Comercio> getStores();
    public List<Comercio> getStoresWithACategory(String category);
}
