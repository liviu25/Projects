package ro.axonsoft.internship.api;

import java.time.LocalTime;

public interface WorkshopDescriptor {
    /**
     *
     * @return workshop's name
     */
    String getName();

    /**
     *
     * @return workshop's theme
     */
    String getTheme();

    /**
     *
     * @return the hall number
     */
    int getHall();

    /**
     *
     * @return workshop's start time
     */
    LocalTime getStartHour();

    /**
     *
     * @return workshop's duration
     */
    int getDuration();


}
