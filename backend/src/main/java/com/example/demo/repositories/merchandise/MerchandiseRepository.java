package com.example.demo.repositories.merchandise;

import com.example.demo.model.merchandise.Merchandise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MerchandiseRepository extends JpaRepository<Merchandise, Long> {
   // List<Merchandise> findByStoreId(Long storeId);
}
