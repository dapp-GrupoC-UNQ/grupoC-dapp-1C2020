package com.example.demo.repositories;

import com.example.demo.model.Comercio;
import com.example.demo.model.RangoHorarioComercio;
import com.example.demo.model.excepciones.NotFoundStoreException;
import com.example.demo.model.merchandise.Merchandise;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository

public class StoreRepository implements IStoreRepository{
    @Override
    public List<Comercio> getStores() {
        RangoHorarioComercio rangoHorario = new RangoHorarioComercio(DayOfWeek.FRIDAY, LocalTime.of(9,0), LocalTime.of(15, 0));
        Comercio store1 = new Comercio("Lo de tito", "Limpieza", "Alsina 123", 4, Arrays.asList("Efectivo"), Arrays.asList(rangoHorario) );
        Comercio store2 = new Comercio("Coto", "Almacen", "Alsina 123", 4, Arrays.asList("Efectivo"), Arrays.asList(rangoHorario) );
        Comercio store3 = new Comercio("Jumbo", "Almacen", "Alsina 123", 4, Arrays.asList("Efectivo"), Arrays.asList(rangoHorario) );
        store1.addMerchandise("Fideos", "Marolio", 24.3, 45);
        store2.addMerchandise("Nesquick", "Nestle", 30.3, 24);
        store2.addMerchandise("Sobre Jugo Naranja", "Tang", 10.3, 24);
        store2.addMerchandise("Leche descremada", "Ilolay", 30.3, 24);
        store2.addMerchandise("Vainillas", "Valente", 23.5, 13);
        store2.addMerchandise("Pure de tomate", "Molto", 37.0, 34);
        return Arrays.asList(store1,store2, store3);
    }
    @Override
    public List<Comercio> getStoresWithACategory(String category) {
        return this.getStores().stream().filter(store -> store.rubro().equals(category)).collect(Collectors.toList());
    }

    @Override
    public List<Merchandise> getProductsFrom(Comercio store) {
        return store.listOfAvailableMerchandise();
    }

    @Override
    public Comercio getStore(String storeName) {
        return this.getStores().stream()
                               .filter(comercio -> comercio.nombre().equals(storeName))
                               .findFirst()
                               .orElseThrow(NotFoundStoreException::new);
    }
}
