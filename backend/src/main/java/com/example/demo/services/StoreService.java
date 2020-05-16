package com.example.demo.services;

import com.example.demo.model.merchandise.Merchandise;
import com.example.demo.model.store.StoreCategory;
import com.example.demo.repositories.IStoreRepository;
import com.example.demo.model.store.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService implements IStoreService {

    @Autowired
    private IStoreRepository storeRepository;

    @Override
    public List<Store> getStores() {
        return storeRepository.getStores();
    }

    @Override
    public List<Store> getStoresWithACategory(StoreCategory category) { return storeRepository.getStoresWithACategory(category);   }

    @Override
    public List<Merchandise> getProductsFromStore(String storeName) {
        return storeRepository.getStore(storeName).listOfAvailableMerchandise();
    }

    @Override
    public Store getStore(String storeName) {
        return storeRepository.getStore(storeName);
    }

    @Override
    public List<Merchandise> getDiscountFromStores() {  return storeRepository.getDiscountFromAllStores();  }
}
