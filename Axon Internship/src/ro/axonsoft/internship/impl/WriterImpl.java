package ro.axonsoft.internship.impl;

import ro.axonsoft.internship.api.SearchResult;
import ro.axonsoft.internship.api.WorkshopDescriptor;
import ro.axonsoft.internship.api.Writer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class WriterImpl implements Writer {
    @Override
    public void writeResult(List<SearchResult> results) {
        for (SearchResult result : results) {
            String filename=result.getStudentName();
            try(PrintWriter printWriter=new PrintWriter(new FileWriter(filename))) {
                for (WorkshopDescriptor workshop : result.getWorkshops()) {
                    String line=new String();
                    line+=  workshop.getName()+";"+
                            workshop.getTheme()+";"+
                            workshop.getHall()+";"+
                            workshop.getStartHour()+";"+
                            workshop.getDuration();
                    printWriter.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
