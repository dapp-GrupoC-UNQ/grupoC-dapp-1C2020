package com.example.demo.controllers;

import com.example.demo.model.merchandise.Merchandise;
import com.example.demo.model.store.StoreCategory;
import com.example.demo.model.user.StoreAdminUser;
import com.example.demo.services.IStoreService;
import com.example.demo.services.users.IUserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.example.demo.model.store.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class StoreController {

    @Autowired
    private IStoreService storeService;
    @Autowired
    private IUserService userService;


    @RequestMapping("/stores")
    public List<Store> getStores(@RequestParam(defaultValue = "") String category) throws JsonProcessingException {
        if(category.isEmpty()){
            return storeService.getStores();
        }
        return storeService.getStoresWithACategory(StoreCategory.valueOf(category));
    }

    @RequestMapping("/stores/{id}")
    public ResponseEntity<Store> getStore(@PathVariable("id") String id){
        Long storeId = Long.parseLong(id);
        return new ResponseEntity<>(storeService.getStore(storeId), HttpStatus.OK);
    };

    @RequestMapping(path="/stores/{id}/products")
    public ResponseEntity<Object> getMessage(@PathVariable("id") Long storeId) {
        List<Merchandise> merchandises = storeService.getProductsFromStore(storeId);
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
