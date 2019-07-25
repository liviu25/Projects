package ro.axonsoft.internship.impl;

import ro.axonsoft.internship.api.SearchResult;
import ro.axonsoft.internship.api.StudentDescriptor;
import ro.axonsoft.internship.api.WorkshopDescriptor;

import java.util.List;

public class SearchResultImpl implements SearchResult {
    private StudentDescriptor student;
    private List<WorkshopDescriptor> workshops;

    public SearchResultImpl(StudentDescriptor student, List<WorkshopDescriptor> workshops) {
        this.student = student;
        this.workshops = workshops;
    }

    @Override
    public String getStudentName() {
        return student.getName();
    }

    @Override
    public List<WorkshopDescriptor> getWorkshops() {
        return workshops;
    }
}
