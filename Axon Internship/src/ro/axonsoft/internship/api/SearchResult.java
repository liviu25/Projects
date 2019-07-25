package ro.axonsoft.internship.api;

import java.util.List;

public interface SearchResult {
    /**
     * The student name for which the search was made
     */
    String getStudentName();
    /**
     * The descriptions of the workshops found for the student
     */
    List<WorkshopDescriptor> getWorkshops();
}
