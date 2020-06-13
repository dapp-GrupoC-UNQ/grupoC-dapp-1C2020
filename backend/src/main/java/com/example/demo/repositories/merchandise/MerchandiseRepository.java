package com.example.demo.repositories.merchandise;

import com.example.demo.model.merchandise.Merchandise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MerchandiseRepository extends JpaRepository<Merchandise, Long> {

    @Query(value = "SELECT DISTINCT FROM (SELECT DISTINCT FROM STORE_MERCHANDISE_LIST INNER JOIN MERCHANDISE ON MERCHANDISE.ID=MERCHANDISE_LIS_ID AND STORE_ID= %:storeId%) AS C",
            nativeQuery = true)
    Optional<List<Merchandise>> getMerchandiseFromStore(@Param("storeId") Long storeId);

}

//SELECT * FROM (SELECT * FROM STORE_MERCHANDISE_LIST INNER JOIN MERCHANDISE ON MERCHANDISE.ID=STORE_MERCHANDISE_LIST.MERCHANDISE_LIST_ID) WHERE STORE_ID=27
