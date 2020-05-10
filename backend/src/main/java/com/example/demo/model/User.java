package com.example.demo.model;

import com.example.demo.model.thresholds.MoneyThreshold;
import com.example.demo.serializers.UserJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.example.demo.model.excepciones.InvalidUsernameOrPasswordException;

@JsonSerialize(using = UserJsonSerializer.class)
public class User {

    private String username;
    private String password;
    private MoneyThreshold moneyThresold = new MoneyThreshold(0.0);

    public User(String username, String password){
        if(username.isEmpty() || password.isEmpty()){
            throw new InvalidUsernameOrPasswordException();
        }
        this.username = username;
        this.password = password;
    }

    public String username() { return this.username; }

    public String password() { return this.password; }

    public Boolean isAdminOfStore() { return false;}

    public Boolean hasMoneyThreshold() {
        return this.moneyThresold.isActive() && this.moneyThresold.moneyLimit() > 0.0;
    }

    public void setMoneyThreshold(MoneyThreshold moneyThreshold) {
        this.moneyThresold = moneyThreshold;
    }

    public void disableMoneyThreshold() {
        this.moneyThresold.disable();
    }

    public void updateMoneyThreshold(Double newMoneyLimit) {
        this.moneyThresold.updateMoneyLimit(newMoneyLimit);
    }

    public Double moneyThresholdLimit() {
        return this.moneyThresold.moneyLimit();
    }
}
