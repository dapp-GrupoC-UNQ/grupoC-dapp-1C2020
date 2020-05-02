package dominio;

import builders.ComercioBuilder;
import builders.UserBuilder;
import model.Comercio;
import model.StoreAdminUser;
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
