package com.example.demo.repositories.merchandise;

import com.example.demo.model.merchandise.Merchandise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchandiseRepository extends JpaRepository<Merchandise, Long> {
}
