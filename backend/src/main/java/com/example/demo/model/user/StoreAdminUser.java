package com.example.demo.model.user;
import com.example.demo.deserializers.StoreAdminUserJsonDeserializer;
import com.example.demo.model.store.Store;
import com.example.demo.serializers.StoreAdminUserJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = StoreAdminUserJsonSerializer.class)
@JsonDeserialize(using = StoreAdminUserJsonDeserializer.class)
public class StoreAdminUser extends User {

    private Store store;

    public StoreAdminUser(String username, String password, Store store) {
        super(username, password);
        this.store = store;
    }

    public StoreAdminUser(){};

    public Store store() {
        return this.store;
    }

    @Override
    public Boolean isAdminOfStore() { return true;}
}
