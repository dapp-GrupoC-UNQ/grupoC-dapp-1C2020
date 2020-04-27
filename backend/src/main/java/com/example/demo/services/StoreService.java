package com.example.demo.services;

import com.example.demo.repositories.IStoreRepository;
import model.Comercio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
}
