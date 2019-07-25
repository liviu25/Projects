package ro.axonsoft.internship.impl;

import ro.axonsoft.internship.api.Reader;
import ro.axonsoft.internship.api.ReaderException;
import ro.axonsoft.internship.api.StudentDescriptor;
import ro.axonsoft.internship.api.WorkshopDescriptor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class WorkshopReader implements Reader<WorkshopDescriptor> {
    @Override
    public List<WorkshopDescriptor> readFile(String filename) {
        List<WorkshopDescriptor> workshopDescriptorList=new ArrayList<>();

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filename)))
        {
            String line;
            while((line = bufferedReader.readLine())!=null) {
                workshopDescriptorList.add(readLine(line));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        } catch (ReaderException e) {
            e.printStackTrace();
        }
        return workshopDescriptorList;
    }

    @Override
    public WorkshopDescriptor readLine(String line) throws ReaderException {
        String[] args=line.split(";");
        String name=args[0];
        String theme=args[1];
        int hall= Integer.parseInt(args[2]);
        LocalTime startHour= LocalTime.parse(args[3]);
        int duration= Integer.parseInt(args[4]);
        return new Workshop(name,theme,hall,startHour,duration);
    }
}
