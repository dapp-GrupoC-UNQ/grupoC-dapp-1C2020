package com.example.demo.deserializers;

import com.example.demo.model.StoreSchedule;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

public class StoreScheduleJsonDeserializer extends JsonDeserializer<StoreSchedule>{
    @Override
    public StoreSchedule deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        List<DayOfWeek> openingDays = Arrays.asList(DayOfWeek.FRIDAY);
        return new StoreSchedule(openingDays, LocalTime.of(9, 0), LocalTime.of(19, 0));
    }
}
