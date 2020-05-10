package com.example.demo.builders;

import com.example.demo.model.Store;
import com.example.demo.model.StoreAdminUser;
import com.example.demo.model.User;
import com.example.demo.model.thresholds.MoneyThreshold;

public class UserBuilder {

    private String username = "Pepe";
    private String password = "123456";

    public static UserBuilder user() {
        return new UserBuilder();
    }

    public User build() {
        return new User(username, password);
    }

    public UserBuilder withUsername(String aUsername) {
        username = aUsername;
        return this;
    }

    public UserBuilder withPassword(String aPassword) {
        password = aPassword;
        return this;
    }

    public StoreAdminUser adminOfStore(Store store) {
        return new StoreAdminUser(username, password, store);
    }

    public User withMoneyThreshold(Double moneyLimit) {
        User user = new User(username, password);
        user.setMoneyThreshold(new MoneyThreshold(moneyLimit));
        return user;
    }
}
