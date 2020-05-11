package com.example.demo.model;

import com.example.demo.model.thresholds.MoneyThreshold;
import com.example.demo.model.ticket.Ticket;
import com.example.demo.serializers.UserJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.example.demo.model.excepciones.InvalidUsernameOrPasswordException;

import java.util.ArrayList;
import java.util.List;

@JsonSerialize(using = UserJsonSerializer.class)
public class User {

    private String username;
    private String password;
    private List<Ticket> purchasesTickets;
    private MoneyThreshold moneyThresold = new MoneyThreshold(0.0);

    public User(String username, String password){
        if(username.isEmpty() || password.isEmpty()){
            throw new InvalidUsernameOrPasswordException();
        }
        this.username = username;
        this.password = password;
        this.purchasesTickets = new ArrayList<>();
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

    public MoneyThreshold moneyThreshold() {
        return this.moneyThresold;
    }

    public Double moneyThresholdLimit() {
        return this.moneyThresold.moneyLimit();
    }

    public Boolean hasTickets() {
        return false;
    }

    public void addTicketOfPurchase(Ticket ticket) {
        this.purchasesTickets.add(ticket);
    }

    public Boolean hasTicketOf(Purchase purchase) {
        return this.purchasesTickets.stream().anyMatch(ticket -> ticket.purchase().equals(purchase));
    }
}
