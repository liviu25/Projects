package ro.axonsoft.internship.api;

import java.time.LocalTime;
import java.util.List;

public interface StudentDescriptor {
    /**
     *
     * @return the student's name
     */
    String getName();

    /**
     *
     * @return start time of availability
     */
    LocalTime getStartHour();

    /**
     *
     * @return end time of availability
     */
    LocalTime getEndHour() ;

    /**
     *
     * @return list of the themes of the workshops he is interested in
     */
    List<String> getThemesList();
}
