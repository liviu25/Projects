package ro.axonsoft.internship.test;

import org.junit.Before;
import org.junit.Test;
import ro.axonsoft.internship.impl.Workshop;

import java.time.LocalTime;

import static org.junit.Assert.*;

public class WorkshopTest {
    Workshop workshop;


    @Before
    public void setUp() throws Exception {
        workshop=new Workshop("Instrumente neconventionale","muzica",12, LocalTime.parse("13:00"),90);
    }

    @Test
    public void getName() {
        assertEquals(workshop.getName(),"Instrumente neconventionale");
    }

    @Test
    public void getTheme() {
        assertEquals(workshop.getTheme(),"muzica");
    }

    @Test
    public void getHall() {
        assertEquals(workshop.getHall(),12);
    }

    @Test
    public void getStartHour() {
        assertEquals(workshop.getStartHour(),LocalTime.parse("13:00"));
    }

    @Test
    public void getDuration() {
        assertEquals(workshop.getDuration(),90);
    }
}