package com.example.demo.repositories;

import com.example.demo.ServingWebContentApplication;
import com.example.demo.config.H2TestProfileJPAConfig;
import com.example.demo.model.AcquiredProduct;
import com.example.demo.repositories.acquiredProducts.AcquiredProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
        ServingWebContentApplication.class,
        H2TestProfileJPAConfig.class})
@ActiveProfiles("test")
public class AcquiredProductRepositoryTest {

    @Autowired
    private AcquiredProductRepository acquiredProductRepository;

    @Test
    public void noneEventIsRetrieved() {
        List<AcquiredProduct> acquiredProducts = acquiredProductRepository.findAll();
        assertTrue(acquiredProducts.isEmpty());
    }
}
