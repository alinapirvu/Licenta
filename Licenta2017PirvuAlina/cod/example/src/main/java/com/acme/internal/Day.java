package com.acme.internal;


import java.util.ArrayList;
import java.util.List;

public class Day {
    public String number;
    public List<Event> eventsForToday;

    public Day() {
        this.number = " ";
        this.eventsForToday = new ArrayList<>();
    }

    public Day(String dayNumber){
        this.number = dayNumber;
        this.eventsForToday = new ArrayList<>();
    }

    public Day(String dayNumber, List<Event> eventsForToday){
        this.number = dayNumber;
        this.eventsForToday = eventsForToday;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public List<Event> getEventsForToday() {
        return eventsForToday;
    }

    public void setEventsForToday(List<Event> eventsForToday) {
        this.eventsForToday = new ArrayList<>();
        for(int i = 0; i < eventsForToday.size(); i++){
            this.eventsForToday.add(i, eventsForToday.get(i));
        }
    }
}
