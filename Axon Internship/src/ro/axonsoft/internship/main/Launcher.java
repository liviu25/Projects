package ro.axonsoft.internship.main;

import ro.axonsoft.internship.api.SearchResult;
import ro.axonsoft.internship.api.StudentDescriptor;
import ro.axonsoft.internship.api.WorkshopDescriptor;
import ro.axonsoft.internship.impl.*;

import java.time.LocalTime;
import java.util.*;

public class Launcher {
    public static void main(String[] args) {
        //read students
        StudentReader studentReader=new StudentReader();
        List<StudentDescriptor> studentDescriptorList = studentReader.readFile("src\\ro\\axonsoft\\internship\\main\\students.csv");

        //read workshops
        WorkshopReader workshopReader=new WorkshopReader();
        List<WorkshopDescriptor> workshopDescriptorList = workshopReader.readFile("src\\ro\\axonsoft\\internship\\main\\workshops.csv");

        WorkshopFinderImpl workshopFinder=new WorkshopFinderImpl(workshopDescriptorList);

        WriterImpl writer=new WriterImpl();
        List<SearchResult> searchResultList=new ArrayList<>();
        studentDescriptorList.forEach(studentDescriptor -> {
            searchResultList.add(workshopFinder.getWorkshops(studentDescriptor));
        });
        writer.writeResult(searchResultList);


    }
}
