package com.example.demo.repositories;

import com.example.demo.model.*;
import com.example.demo.model.excepciones.NotFoundStoreException;
import com.example.demo.model.merchandise.Merchandise;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository

public class StoreRepository implements IStoreRepository{
    @Override
    public List<Store> getStores() {
        Discount noDiscount = new NoDescount();
        RangoHorarioComercio rangoHorario = new RangoHorarioComercio(DayOfWeek.FRIDAY, LocalTime.of(9,0), LocalTime.of(15, 0));
        Store store1 = new Store("Lo de tito", "Limpieza", "Alsina 123", 4, Arrays.asList("Efectivo"), Arrays.asList(rangoHorario) );
        Store store2 = new Store("Coto", "Almacen", "Alsina 123", 4, Arrays.asList("Efectivo"), Arrays.asList(rangoHorario) );
        Store store3 = new Store("Jumbo", "Almacen", "Alsina 123", 4, Arrays.asList("Efectivo"), Arrays.asList(rangoHorario) );
        store1.addMerchandise("Fideos", "Marolio", 24.3, 45);
        store2.addMerchandise("Nesquick", "Nestle", 30.3, 24);
        store2.addMerchandise("Sobre Jugo Naranja", "Tang", 10.3, 24);
        store2.addMerchandise("Leche descremada", "Ilolay", 30.3, 24);
        return Arrays.asList(store1,store2, store3);
    }
    @Override
    public List<Store> getStoresWithACategory(String category) {
        return this.getStores().stream().filter(store -> store.storeCategory().equals(category)).collect(Collectors.toList());
    }

    @Override
    public List<Merchandise> getProductsFrom(Store store) {
        return store.listOfAvailableMerchandise();
    }

    @Override
    public Store getStore(String storeName) {
        return this.getStores().stream()
                               .filter(comercio -> comercio.name().equals(storeName))
                               .findFirst()
                               .orElseThrow(NotFoundStoreException::new);
    }

    @Override
    public List<Merchandise> getDiscountFromAllStores() {
        Discount discount = new PercentageDiscount(20, LocalDate.of(2020,5,5), LocalDate.of(2020,5,10));
        Merchandise merchandise = new Merchandise("Nesquick", "Nestle", 30.3, 24, discount);
        return Arrays.asList(merchandise);
    }
}
