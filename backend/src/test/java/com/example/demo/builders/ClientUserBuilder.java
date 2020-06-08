package com.example.demo.builders;

import com.example.demo.model.store.Store;
import com.example.demo.model.user.StoreAdminUser;
import com.example.demo.model.user.ClientUser;

import java.util.Random;

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

    public ClientUser withEmptyUsername() {
        ClientUser client = this.build();
        client.setUsername("");
        return client;
    }

    public ClientUser withEmptyPassword() {
        ClientUser client = this.build();
        client.setPassword("");
        return client;
    }
}
