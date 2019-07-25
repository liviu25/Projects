package ro.axonsoft.internship.impl;

import ro.axonsoft.internship.api.StudentDescriptor;

import java.time.LocalTime;
import java.util.List;

public class Student implements StudentDescriptor {
    private String name;
    private LocalTime startHour,endHour;
    private List<String> themesList;

    public Student(String name, LocalTime startHour, LocalTime endHour, List<String> themesList) {
        this.name = name;
        this.startHour = startHour;
        this.endHour = endHour;
        this.themesList = themesList;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public LocalTime getStartHour() {
        return startHour;
    }

    @Override
    public LocalTime getEndHour() {
        return endHour;
    }

    @Override
    public List<String> getThemesList() {
        return themesList;
    }

}
