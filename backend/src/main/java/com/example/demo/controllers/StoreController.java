package com.example.demo.controllers;

import com.example.demo.model.merchandise.Merchandise;
import com.example.demo.model.store.StoreCategory;
import com.example.demo.sendMail.MailSender;
import com.example.demo.services.IStoreService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.example.demo.model.store.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class StoreController {

    public static final String MODEL_ATTRIBUTE_TODO = "hola";
    @Autowired
    private IStoreService storeService;
    @Autowired
    private JavaMailSender javaMailSender;


    @RequestMapping("/stores")
    public List<Store> getStores(@RequestParam(defaultValue = "") String category) throws JsonProcessingException {
        if(category.isEmpty()){
            return storeService.getStores();
        }
        return storeService.getStoresWithACategory(StoreCategory.valueOf(category));
    }

    @RequestMapping(path="/stores/{name}/products")
    public ResponseEntity<Object> getMessage(@PathVariable("name") String storeName) {
        Store store = storeService.getStore(storeName);
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

 /*   @RequestMapping("/mail")
    public ResponseEntity<String> sendEMail(){
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom("store@gmail.com");
        mailMessage.setTo("luliialonso@hotmail.com");
        mailMessage.setSubject("HALAAAAAA");
        mailMessage.setText("PARALA LAKAAA");
        javaMailSender.send(mailMessage);
        return new ResponseEntity<String>("tu vieja", HttpStatus.OK);
    }*/
}
