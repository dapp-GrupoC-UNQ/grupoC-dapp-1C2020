package com.example.demo.controllers;

import com.example.demo.model.merchandise.Merchandise;
import com.example.demo.services.IStoreService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.example.demo.model.Comercio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class StoreController {

    public static final String MODEL_ATTRIBUTE_TODO = "hola";
    @Autowired
    private IStoreService storeService;


    @RequestMapping("/stores")
    public List<Comercio> getStores(@RequestParam(defaultValue = "") String category) throws JsonProcessingException {
        if(category.isEmpty()){
            return storeService.getStores();
        }
        return storeService.getStoresWithACategory(category);
    }

    @RequestMapping(path="/stores/{name}/products")
    public ResponseEntity<Object> getMessage(@PathVariable("name") String storeName) {
        Comercio store = storeService.getStore(storeName);
        List<Merchandise> merchandises = storeService.getProductsFromStore(storeName);
        return generateProductsResponse(merchandises);
    }

    @RequestMapping(path="/stores/discounts")
    public List<Merchandise> getDiscountFromAllStores(){
        return storeService.getDiscountFromStores();
    }

    private ResponseEntity<Object> generateProductsResponse(List<Merchandise> merchandises) {
        return new ResponseEntity<>(merchandises, HttpStatus.OK);
    }
}
