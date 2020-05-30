package com.example.demo.model;

import com.example.demo.model.store.Store;

public class StoreAdminUser extends ClientUser {

    private Store store;

    public StoreAdminUser(String username, String password, Store store) {
        super(username, password);
        this.store = store;
    }

    public Store store() {
        return this.store;
    }

    @Override
    public Boolean isAdminOfStore() { return true;}
}
