package com.example.demo.controllers;

import com.example.demo.services.IStoreService;
import com.fasterxml.jackson.core.JsonProcessingException;
import model.Comercio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class StoreController {

    @Autowired
    private IStoreService storeService;

    @RequestMapping("/stores")
    public List<Comercio> getStores() throws JsonProcessingException {
        return storeService.getStores();
    }
}
