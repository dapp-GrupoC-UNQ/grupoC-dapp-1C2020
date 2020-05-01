package com.example.demo.repositories;

import model.Comercio;
import model.RangoHorarioComercio;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@Repository
@Qualifier("storesRepository")
public class StoreRepository implements IStoreRepository{
    @Override
    public List<Comercio> getStores() {
        RangoHorarioComercio rangoHorario = new RangoHorarioComercio(DayOfWeek.FRIDAY, LocalTime.of(9,0), LocalTime.of(15, 0));
        Comercio store1 = new Comercio("Lo de tito", "Almacen", "Alsina 123", 4, Arrays.asList("Efectivo"), Arrays.asList(rangoHorario) );
        Comercio store2 = new Comercio("Coto", "Almacen", "Alsina 123", 4, Arrays.asList("Efectivo"), Arrays.asList(rangoHorario) );
        return Arrays.asList(store1,store2);
    }
}
