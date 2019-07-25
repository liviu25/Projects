package ro.axonsoft.internship.impl;

import ro.axonsoft.internship.api.WorkshopDescriptor;

import java.time.LocalTime;

public class Workshop implements WorkshopDescriptor {
    private String name,theme;
    private int hall;
    private LocalTime startHour;
    private int duration;

    public Workshop(String name, String theme, int hall, LocalTime startHour, int duration) {
        this.name = name;
        this.theme = theme;
        this.hall = hall;
        this.startHour = startHour;
        this.duration = duration;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getTheme() {
        return theme;
    }

    @Override
    public int getHall() {
        return hall;
    }

    @Override
    public LocalTime getStartHour() {
        return startHour;
    }

    @Override
    public int getDuration() {
        return duration;
    }

}
