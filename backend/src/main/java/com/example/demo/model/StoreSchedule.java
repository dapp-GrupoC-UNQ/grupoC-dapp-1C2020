package com.example.demo.model;

import com.example.demo.model.exceptions.WrongScheduleException;
import com.example.demo.model.store.Store;
import com.example.demo.serializers.StoreScheduleSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

@JsonSerialize(using = StoreScheduleSerializer.class)
public class StoreSchedule {

    private List<DayOfWeek> scheduleDays;
    private LocalTime openingTime;
    private LocalTime closingTime;

    public StoreSchedule(){};

    public StoreSchedule(List<DayOfWeek> daysList, LocalTime startTime, LocalTime endTime) {
        if(endTime.isBefore(startTime)){ throw new WrongScheduleException(); }
        scheduleDays = daysList;
        openingTime = startTime;
        closingTime = endTime;
    }

    public Boolean availableOnDay(DayOfWeek day) {
        return scheduleDays.contains(day);
    }

    public boolean isAvailableOn(DayOfWeek day, LocalTime hour) {
        return scheduleDays.contains(day)  && (openingTime.isBefore(hour) || openingTime.equals(hour))
                                          && (closingTime.isAfter(hour) || closingTime.equals(hour));
    }

    public LocalTime openingTime() {
        return this.openingTime;
    }

    public LocalTime closingTime() { return this.closingTime; }

    public List<DayOfWeek> days() {
        return scheduleDays;
    }
}
