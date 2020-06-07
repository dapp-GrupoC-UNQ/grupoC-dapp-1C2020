package com.example.demo.services;

import com.example.demo.model.exceptions.NotFoundStoreException;
import com.example.demo.model.merchandise.Merchandise;
import com.example.demo.model.store.StoreCategory;
import com.example.demo.model.user.StoreAdminUser;
import com.example.demo.model.store.Store;
import com.example.demo.model.validator.EntityValidator;
import com.example.demo.repositories.StoreRepository;
import com.example.demo.repositories.merchandise.MerchandiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService implements IStoreService {

    private EntityValidator entityVAlidator = new EntityValidator();

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private MerchandiseRepository merchandiseRepository;

    @Override
    public List<Store> getStores() {
        return storeRepository.findAll();
    }

    public List<Store> getStoresWithACategory(StoreCategory category) { return null; }

    public List<Merchandise> getProductsFromStore(Long storeId) {
        //return merchandiseRepository.findByStoreId(storeId);
        return null;
    }

   @Override
    public Store getStore(Long storeId) {
        return storeRepository.findById(storeId).orElseThrow(NotFoundStoreException::new);
    }

    @Override
    public List<Merchandise> getDiscountFromStores() {  return null; }

   @Override
   public Store addStore(Store store) {
       validateStore(store);
       storeRepository.save(store);
       return store;
   }

    private void validateStore(Store aStore) {
        entityVAlidator.validateStore(aStore);
    }

    @Override
    public StoreAdminUser addAdmin() {
        return null;
    }
}
