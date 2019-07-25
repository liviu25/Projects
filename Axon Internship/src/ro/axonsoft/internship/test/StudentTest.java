package ro.axonsoft.internship.test;

import org.junit.Before;
import org.junit.Test;
import ro.axonsoft.internship.impl.Student;

import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class StudentTest {
    List<String> themesList;
    Student student;

    @Before
    public void setUp() throws Exception {
        themesList=new LinkedList<>();
        themesList.add("pictura");
        student=new Student("Vasile Pop", LocalTime.parse("10:30"), LocalTime.parse("15:30"),themesList);
    }

    @Test
    public void getName() {

        assertEquals(student.getName(),"Vasile Pop");
    }

    @Test
    public void getStartHour() {
        assertEquals(student.getStartHour(),LocalTime.parse("10:30"));
    }

    @Test
    public void getEndHour() {
        assertEquals(student.getEndHour(),LocalTime.parse("15:30"));
    }

    @Test
    public void getThemesList() {
        assertEquals(student.getThemesList().get(0),"pictura");
    }
}