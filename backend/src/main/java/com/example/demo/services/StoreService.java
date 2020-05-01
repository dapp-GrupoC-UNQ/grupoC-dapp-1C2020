package com.example.demo.services;

import com.example.demo.repositories.IStoreRepository;
import model.Comercio;
import model.RangoHorarioComercio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@Service
public class StoreService implements IStoreService {

    @Autowired
    @Qualifier("storesRepository")
    private IStoreRepository storeRepository;

    @Override
    public List<Comercio> getStores() {
        return storeRepository.getStores();
    }
}
