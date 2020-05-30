package com.example.demo.model.user;

import com.example.demo.model.Bill;
import com.example.demo.model.exceptions.NotFoundCategoryMoneyThresholdForThisUser;
import com.example.demo.model.merchandise.MerchandiseCategory;
import com.example.demo.model.thresholds.CategoryMoneyThreshold;
import com.example.demo.model.thresholds.MoneyThreshold;
import com.example.demo.model.user.User;
import com.example.demo.serializers.UserJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;
import java.util.List;

@JsonSerialize(using = UserJsonSerializer.class)
public class ClientUser extends User {

    private List<Bill> billOfPurchase;
    private MoneyThreshold moneyThresold = new MoneyThreshold(0.0);
    private List<CategoryMoneyThreshold> categoryMoneyThresholds = new ArrayList<>();

    public ClientUser(String username, String password){
        super(username, password);
        this.billOfPurchase = new ArrayList<>();
    }


    @Override
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

    public Double moneyThresholdLimit() {
        return this.moneyThresold.moneyLimit();
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

    public Integer quantityOfBills() {
        return this.billOfPurchase.size();
    }

    public Boolean hasBill(Bill aBill) {
        return this.billOfPurchase.contains(aBill);
    }
}
