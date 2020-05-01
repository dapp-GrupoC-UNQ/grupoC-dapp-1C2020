package com.example.demo.repositories;

import model.Comercio;
import model.RangoHorarioComercio;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Qualifier("storesRepository")
public class StoreRepository implements IStoreRepository{
    @Override
    public List<Comercio> getStores() {
        RangoHorarioComercio rangoHorario = new RangoHorarioComercio(DayOfWeek.FRIDAY, LocalTime.of(9,0), LocalTime.of(15, 0));
        Comercio store1 = new Comercio("Lo de tito", "Limpieza", "Alsina 123", 4, Arrays.asList("Efectivo"), Arrays.asList(rangoHorario) );
        Comercio store2 = new Comercio("Coto", "Almacen", "Alsina 123", 4, Arrays.asList("Efectivo"), Arrays.asList(rangoHorario) );
        return Arrays.asList(store1,store2);
    }
    @Override
    public List<Comercio> getStoresWithACategory(String category) {
        return this.getStores().stream().filter(store -> store.rubro().equals(category)).collect(Collectors.toList());
    }
}
