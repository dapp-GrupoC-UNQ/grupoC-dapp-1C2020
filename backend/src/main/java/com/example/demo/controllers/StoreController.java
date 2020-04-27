package com.example.demo.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Comercio;
import model.RangoHorarioComercio;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@RestController
public class StoreController {
    @RequestMapping("/stores")
    public @ResponseBody
    List<Comercio> getStores() throws JsonProcessingException {
        RangoHorarioComercio rangoComercio = new RangoHorarioComercio(DayOfWeek.FRIDAY, LocalTime.of(3, 45), LocalTime.of(15, 34));
        Comercio store = new Comercio("Lo de tito", "Limpieza", "Calle 6 5048", 4, Arrays.asList("Tarjeta"), Arrays.asList(rangoComercio));
        return Arrays.asList(store);
    }
}
