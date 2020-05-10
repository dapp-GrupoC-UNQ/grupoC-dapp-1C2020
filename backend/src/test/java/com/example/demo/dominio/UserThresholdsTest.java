package com.example.demo.dominio;

import com.example.demo.builders.MoneyThresholdBuilder;
import com.example.demo.builders.UserBuilder;
import com.example.demo.model.User;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserThresholdsTest {

    @Test
    public void aNewUserDoesNotHaveAnyThreshold() {
        User user = UserBuilder.user().build();
        assertFalse(user.hasMoneyThreshold());
    }

    @Test
    public void aUserCanSetAMoneyThreshold() {
        User user = UserBuilder.user().build();
        user.setMoneyThreshold(MoneyThresholdBuilder.aMoneyThreshold().build());
        assertTrue(user.hasMoneyThreshold());
    }

    @Test
    public void aUserCanDisableItsMoneyThreshold() {
        User user = UserBuilder.user().withMoneyThreshold(1000.0);
        user.disableMoneyThreshold();
        assertFalse(user.hasMoneyThreshold());
    }

    @Test
    public void aUserCanUpdateItsMoneyThreshold() {
        User user = UserBuilder.user().withMoneyThreshold(1000.0);
        user.updateMoneyThreshold(2000.0);
        assertEquals(user.moneyThresholdLimit(), 2000.0);
    }
}
