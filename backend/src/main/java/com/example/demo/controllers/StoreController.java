package com.example.demo.controllers;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StoreController {

    @RequestMapping("/store")
    public String sayHi() {
        return "Hi";
    }
}
