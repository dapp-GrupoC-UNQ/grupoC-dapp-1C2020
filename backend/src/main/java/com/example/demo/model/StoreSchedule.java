package com.example.demo.model;

import com.example.demo.model.exceptions.ScheduleNotAvailableException;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class StoreSchedule {

    DayOfWeek attentionDay;
    LocalTime openingTime;
    LocalTime closingTime;

    public StoreSchedule(DayOfWeek aDay, LocalTime startTime, LocalTime endTime) {
        if(endTime.isBefore(startTime)){ throw new ScheduleNotAvailableException(); }
        attentionDay = aDay;
        openingTime = startTime;
        closingTime = endTime;
    }

    public DayOfWeek getDay() {
        return attentionDay;
    }

    public boolean isAvailableOnTime(DayOfWeek dia, LocalTime hora) {
        return attentionDay.equals(dia)  && (openingTime.isBefore(hora) || openingTime.equals(hora))
                                          && (closingTime.isAfter(hora) || closingTime.equals(hora));
    }

    public LocalTime openingTime() {
        return this.openingTime;
    }
}
