package ro.axonsoft.internship.test;

import org.junit.Before;
import org.junit.Test;
import ro.axonsoft.internship.api.SearchResult;
import ro.axonsoft.internship.api.StudentDescriptor;
import ro.axonsoft.internship.api.WorkshopDescriptor;
import ro.axonsoft.internship.impl.SearchResultImpl;
import ro.axonsoft.internship.impl.Student;
import ro.axonsoft.internship.impl.Workshop;

import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class SearchResultImplTest {

    SearchResult searchResult;
    @Before
    public void setUp() throws Exception {
        List<String> themesList=new LinkedList<>();
        themesList.add("pictura");
        StudentDescriptor student=new Student("Vasile Pop", LocalTime.parse("11:30"), LocalTime.parse("15:30"),themesList);
        List<WorkshopDescriptor> workshopDescriptorList=new LinkedList<>();
        workshopDescriptorList.add(new Workshop("Instrumente neconventionale","muzica",12,LocalTime.parse("13:30"),90));
        workshopDescriptorList.add(new Workshop("Povesti creative","lectura",12,LocalTime.parse("13:00"),90));
        workshopDescriptorList.add(new Workshop("Pictura pe sticla","pictura",12,LocalTime.parse("13:00"),90));

        searchResult=new SearchResultImpl(student,workshopDescriptorList);
    }

    @Test
    public void getStudentName() {
        assertEquals(searchResult.getStudentName(),"Vasile Pop");
    }

    @Test
    public void getWorkshops() {
        assertEquals(searchResult.getWorkshops().size(),3);
    }
}