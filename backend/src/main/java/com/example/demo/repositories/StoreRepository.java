package com.example.demo.repositories;

import model.Comercio;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier("storesRepository")
public class StoreRepository implements IStoreRepository{
    @Override
    public List<Comercio> getStores() {
        return null;
    }
}
