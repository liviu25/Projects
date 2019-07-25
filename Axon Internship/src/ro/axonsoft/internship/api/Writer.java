package ro.axonsoft.internship.api;

import java.util.List;

public interface Writer {
    /**
     * Writes the result information for each student
     */
    void writeResult(final List<SearchResult> results);
}
