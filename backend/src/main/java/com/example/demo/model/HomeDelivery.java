package com.example.demo.model;

import com.example.demo.model.exceptions.OptionNotAvailableForThisDeliveryType;

import java.time.LocalDateTime;

public class HomeDelivery implements DeliveryType {

    private String deliveryAddress;

    public HomeDelivery(String address){
        this.deliveryAddress = address;
    }
    @Override
    public String deliveryAddress() {
        return this.deliveryAddress;
    }

    @Override
    public LocalDateTime pickUpDate() { throw new OptionNotAvailableForThisDeliveryType();  }
}
