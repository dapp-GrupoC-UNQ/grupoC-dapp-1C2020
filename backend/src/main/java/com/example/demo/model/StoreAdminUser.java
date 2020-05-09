package com.example.demo.model;

public class StoreAdminUser extends User {

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
