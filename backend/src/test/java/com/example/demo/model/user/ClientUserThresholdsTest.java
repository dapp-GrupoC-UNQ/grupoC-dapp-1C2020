package com.example.demo.model.user;

import com.example.demo.builders.MoneyThresholdBuilder;
import com.example.demo.builders.UserBuilder;
import com.example.demo.model.ClientUser;
import com.example.demo.model.exceptions.NotFoundCategoryMoneyThresholdForThisUser;
import com.example.demo.model.merchandise.MerchandiseCategory;
import org.junit.Test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

public class ClientUserThresholdsTest {

    @Test
    public void aNewUserDoesNotHaveAnyThreshold() {
        ClientUser clientUser = UserBuilder.user().build();
        assertFalse(clientUser.hasMoneyThreshold());
    }

    @Test
    public void aUserCanSetAMoneyThreshold() {
        ClientUser clientUser = UserBuilder.user().build();
        clientUser.setMoneyThreshold(MoneyThresholdBuilder.aMoneyThreshold().build());
        assertTrue(clientUser.hasMoneyThreshold());
    }

    @Test
    public void aUserCanDisableItsMoneyThreshold() {
        ClientUser clientUser = UserBuilder.user().withMoneyThreshold(1000.0);
        clientUser.disableMoneyThreshold();
        assertFalse(clientUser.hasMoneyThreshold());
    }

    @Test
    public void aUserCanUpdateItsMoneyThreshold() {
        ClientUser clientUser = UserBuilder.user().withMoneyThreshold(1000.0);
        clientUser.updateMoneyThreshold(2000.0);
        assertEquals(clientUser.moneyThresholdLimit(), 2000.0);
    }

    @Test
    public void aUserCanAddACategoryMoneyThreshold() {
        ClientUser clientUser = UserBuilder.user().build();
        clientUser.addCategoryMoneyThreshold(MerchandiseCategory.BUTCHERS, 400.0);
        assertTrue(clientUser.hasCategoryLimitOf(MerchandiseCategory.BUTCHERS));
    }

    @Test
    public void aUserCanUpdateItsThresholdForACategory() {
        ClientUser clientUser = UserBuilder.user().build();
        clientUser.addCategoryMoneyThreshold(MerchandiseCategory.BUTCHERS, 400.0);
        clientUser.updateCategoryMoneyThreshold(MerchandiseCategory.BUTCHERS, 500.0);
        assertEquals(500.0, clientUser.categoryMoneyThresholdOf(MerchandiseCategory.BUTCHERS).moneyLimit());
    }

    @Test
    public void aUserCannotUpdateACategoryMoneyThresholdWithoutFirstAddingIt() {
        ClientUser clientUser = UserBuilder.user().build();
        assertThrows(NotFoundCategoryMoneyThresholdForThisUser.class, () -> clientUser.updateCategoryMoneyThreshold(MerchandiseCategory.BUTCHERS, 500.0));

    }
}
