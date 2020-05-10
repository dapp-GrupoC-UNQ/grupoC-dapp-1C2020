package com.example.demo.dominio;

import com.example.demo.builders.MoneyThresholdBuilder;
import com.example.demo.builders.PurchaseBuilder;
import com.example.demo.model.Purchase;
import com.example.demo.model.thresholds.MoneyThreshold;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MoneyThresholdTest {

    @Test
    public void aMoneyThresholdHasALimitValue() {
        Double aLimit = 3000.0;
        MoneyThreshold moneyThreshold = MoneyThresholdBuilder.aMoneyThreshold().withMoneyLimit(aLimit).build();
        assertEquals(moneyThreshold.moneyLimit(), aLimit);
    }

    @Test
    public void whenCreatingAMoneyThresholdIsActiveByDefault() {
        MoneyThreshold moneyThreshold = MoneyThresholdBuilder.aMoneyThreshold().build();
        assertTrue(moneyThreshold.isActive());
    }

    @Test
    public void aDisabledMoneyThresholdIsNoMoreActive() {
        MoneyThreshold moneyThreshold = MoneyThresholdBuilder.aMoneyThreshold().build();
        moneyThreshold.disable();
        assertFalse(moneyThreshold.isActive());
    }

    @Test
    public void aDisabledMoneyThresholdCanBeEnabled() {
        MoneyThreshold moneyThreshold = MoneyThresholdBuilder.aMoneyThreshold().whichIsDisabled();
        moneyThreshold.enable();
        assertTrue(moneyThreshold.isActive());
    }

}
