package com.example.demo.model.user;

import com.example.demo.builders.StoreBuilder;
import com.example.demo.builders.UserBuilder;
import com.example.demo.model.Store;
import com.example.demo.model.StoreAdminUser;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StoreAdminUserTest {

    @Test
    public void aStoreAdminUserKnowsItsStore() {
        Store store = StoreBuilder.aStore().build();
        StoreAdminUser storeAdminUser = UserBuilder.user().adminOfStore(store);
        assertEquals(storeAdminUser.store(), store);
    }

    @Test
    public void aStoreAdminUserIsAStoreAdmin() {
        Store store = StoreBuilder.aStore().build();
        StoreAdminUser storeAdminUser = UserBuilder.user().adminOfStore(store);
        assertTrue(storeAdminUser.isAdminOfStore());
    }

}
