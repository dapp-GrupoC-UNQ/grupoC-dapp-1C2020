package com.example.demo.repositories;
import com.example.demo.model.store.Store;

import com.example.demo.model.store.StoreCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository

public interface StoreRepository extends JpaRepository<Store, Long> {

    //List<Store> getStoresWithACategory(StoreCategory grocery);
}
