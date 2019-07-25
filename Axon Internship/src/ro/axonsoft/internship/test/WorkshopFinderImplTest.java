package ro.axonsoft.internship.test;

import org.junit.Test;
import ro.axonsoft.internship.api.ReaderException;
import ro.axonsoft.internship.api.SearchResult;
import ro.axonsoft.internship.api.StudentDescriptor;
import ro.axonsoft.internship.api.WorkshopDescriptor;
import ro.axonsoft.internship.impl.*;

import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class WorkshopFinderImplTest {

    @Test
    public void getWorkshops() {
        List<String> themesList=new LinkedList<>();
        themesList.add("pictura");
        StudentDescriptor student=new Student("Vasile Pop",LocalTime.parse("10:30"), LocalTime.parse("15:30"),themesList);
        List<WorkshopDescriptor> workshopDescriptorList=new LinkedList<>();
        workshopDescriptorList.add(new Workshop("Instrumente neconventionale","muzica",12,LocalTime.parse("13:00"),90));
        workshopDescriptorList.add(new Workshop("Povesti creative","lectura",12,LocalTime.parse("13:00"),90));
        workshopDescriptorList.add(new Workshop("Pictura pe sticla","pictura",12,LocalTime.parse("13:00"),90));

        WorkshopFinderImpl workshopFinder=new WorkshopFinderImpl(workshopDescriptorList);
        SearchResult workshops = workshopFinder.getWorkshops(student);
        assertEquals(workshops.getWorkshops().get(0).getName(),"Pictura pe sticla");
    }
}