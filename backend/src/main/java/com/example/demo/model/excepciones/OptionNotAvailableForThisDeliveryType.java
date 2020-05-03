package com.example.demo.model.excepciones;

public class OptionNotAvailableForThisDeliveryType extends RuntimeException {
    public OptionNotAvailableForThisDeliveryType(){ super("Option not available for this delivery type.");}
}

