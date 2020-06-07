package com.example.demo.repositories;

import com.example.demo.builders.MerchandiseBuilder;
import com.example.demo.model.merchandise.Merchandise;
import com.example.demo.repositories.merchandise.MerchandiseRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MerchandiseRepositoryTest {

    @Autowired
    private MerchandiseRepository merchandiseRepository;

    @Test
    public void aMerchandiseIsRetrieved() {
        Merchandise merchandise = MerchandiseBuilder.aMerchandise().build();
        merchandiseRepository.save(merchandise);

        Optional<Merchandise> retrievedMerchandise = merchandiseRepository.findById(merchandise.id());
        assertThat(retrievedMerchandise).hasValue(merchandise);
    }
}
