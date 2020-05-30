package com.example.demo.builders;

import com.example.demo.model.StoreAdminUser;
import com.example.demo.model.store.Store;

public class StoreAdminBuilder {

    private String username = "StoreAdmin";
    private String password = "StoreAdminPassword";
    private Store store = StoreBuilder.aStore().build();

    public static StoreAdminBuilder aStoreAdmin() { return new StoreAdminBuilder(); }

    public StoreAdminUser build() {
        return new StoreAdminUser(username, password, store);
    }

    public StoreAdminBuilder withUsername(String aUsername) {
        username = aUsername;
        return this;
    }

    public StoreAdminBuilder withPassword(String aPassword) {
        password = aPassword;
        return this;
    }
}
