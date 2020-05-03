package com.example.demo.services;

import com.example.demo.repositories.IStoreRepository;
import com.example.demo.model.Comercio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService implements IStoreService {

    @Autowired
    private IStoreRepository storeRepository;

    @Override
    public List<Comercio> getStores() {
        return storeRepository.getStores();
    }

    @Override
    public List<Comercio> getStoresWithACategory(String category) { return storeRepository.getStoresWithACategory(category);   }
}
