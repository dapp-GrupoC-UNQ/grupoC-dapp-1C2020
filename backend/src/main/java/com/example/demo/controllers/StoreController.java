package com.example.demo.controllers;

import com.example.demo.services.IStoreService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.example.demo.model.Comercio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController

public class StoreController {

    @Autowired
    private IStoreService storeService;

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/stores")
    public List<Comercio> getStores(@RequestParam(defaultValue = "") String category) throws JsonProcessingException {
        if(category.isEmpty()){
            return storeService.getStores();
        }
        return storeService.getStoresWithACategory(category);
    }
}
