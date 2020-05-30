package com.example.demo.builders;

import com.example.demo.model.store.Store;
import com.example.demo.model.user.StoreAdminUser;
import com.example.demo.model.user.ClientUser;

public class ClientUserBuilder {

    private String username = "Pepe";
    private String password = "123456";

    public static ClientUserBuilder user() {
        return new ClientUserBuilder();
    }

    public ClientUser build() {
        return new ClientUser(username, password);
    }

    public ClientUserBuilder withUsername(String aUsername) {
        username = aUsername;
        return this;
    }

    public ClientUserBuilder withPassword(String aPassword) {
        password = aPassword;
        return this;
    }

    public StoreAdminUser adminOfStore(Store store) {
        return new StoreAdminUser(username, password, store);
    }

    public ClientUser withMoneyThreshold(Double moneyLimit) {
        ClientUser clientUser = new ClientUser(username, password);
        clientUser.updateMoneyThreshold(moneyLimit);
        return clientUser;
    }
}
