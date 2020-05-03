package com.example.demo.dominio;

import com.example.demo.builders.ComercioBuilder;
import com.example.demo.builders.UserBuilder;
import com.example.demo.model.Comercio;
import com.example.demo.model.StoreAdminUser;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StoreAdminUserTest {

    @Test
    public void aStoreAdminUserKnowsItsStore() {
        Comercio store = ComercioBuilder.unComercio().build();
        StoreAdminUser storeAdminUser = UserBuilder.user().adminOfStore(store);
        assertEquals(storeAdminUser.store(), store);
    }

    @Test
    public void aStoreAdminUserIsAStoreAdmin() {
        Comercio store = ComercioBuilder.unComercio().build();
        StoreAdminUser storeAdminUser = UserBuilder.user().adminOfStore(store);
        assertTrue(storeAdminUser.isAdminOfStore());
    }

}
