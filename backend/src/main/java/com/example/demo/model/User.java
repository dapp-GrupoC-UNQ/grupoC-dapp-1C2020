package com.example.demo.model;

import com.example.demo.model.exceptions.NotFoundCategoryMoneyThresholdForThisUser;
import com.example.demo.model.exceptions.UserDoesNotHaveTicketException;
import com.example.demo.model.merchandise.MerchandiseCategory;
import com.example.demo.model.purchase.Bill;
import com.example.demo.model.thresholds.CategoryMoneyThreshold;
import com.example.demo.model.thresholds.MoneyThreshold;
import com.example.demo.model.ticket.Ticket;
import com.example.demo.serializers.UserJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.example.demo.model.exceptions.InvalidUsernameOrPasswordException;

import java.util.ArrayList;
import java.util.List;

@JsonSerialize(using = UserJsonSerializer.class)
public class User {

    private String username;
    private String password;
    private List<Bill> billOfPurchase;
    private MoneyThreshold moneyThresold = new MoneyThreshold(0.0);
    private List<CategoryMoneyThreshold> categoryMoneyThresholds = new ArrayList<>();

    public User(String username, String password){
        if(username.isEmpty() || password.isEmpty()){
            throw new InvalidUsernameOrPasswordException();
        }
        this.username = username;
        this.password = password;
        this.billOfPurchase = new ArrayList<>();
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

    public void addCategoryMoneyThreshold(MerchandiseCategory category, Double moneyLimit) {
        if(!this.hasCategoryLimitOf(category)) {
            this.categoryMoneyThresholds.add(new CategoryMoneyThreshold(moneyLimit, category));
        }
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

    public Boolean hasCategoryLimitOf(MerchandiseCategory category) {
        return this.categoryMoneyThresholds.stream().anyMatch(categoryMoneyThreshold -> categoryMoneyThreshold.category().equals(category));
    }

    public void updateCategoryMoneyThreshold(MerchandiseCategory category, Double newMoneyLimit) {
        this.categoryMoneyThresholdOf(category).updateMoneyLimit(newMoneyLimit);
    }

    public MoneyThreshold categoryMoneyThresholdOf(MerchandiseCategory category) {
        return this.categoryMoneyThresholds.stream().filter(categoryMoneyThreshold -> categoryMoneyThreshold.category().equals(category))
                .findFirst()
                .orElseThrow(NotFoundCategoryMoneyThresholdForThisUser::new);
    }

    public void addBillOfPurchase(Bill bill) {
        this.billOfPurchase.add(bill);
    }

    public Boolean hasABill() {
        return !this.billOfPurchase.isEmpty();
    }


    public Integer quantityOfBills() {
        return this.billOfPurchase.size();
    }
}
