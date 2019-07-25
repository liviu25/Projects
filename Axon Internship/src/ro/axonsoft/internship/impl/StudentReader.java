package ro.axonsoft.internship.impl;

import ro.axonsoft.internship.api.Reader;
import ro.axonsoft.internship.api.ReaderException;
import ro.axonsoft.internship.api.StudentDescriptor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class StudentReader implements Reader<StudentDescriptor> {

    @Override
    public List<StudentDescriptor> readFile(String filename) {
        List<StudentDescriptor> studentDescriptorList=new ArrayList<>();

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filename)))
        {
            String line;
            while((line = bufferedReader.readLine())!=null) {
                studentDescriptorList.add(readLine(line));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        } catch (ReaderException e) {
            e.printStackTrace();
    }
        return studentDescriptorList;
    }

    @Override
    public StudentDescriptor readLine(String line) throws ReaderException {
        String[] args=line.split(";");
        String name=args[0];
        LocalTime startHour= LocalTime.parse(args[1]);
        LocalTime endHour=LocalTime.parse(args[2]);
        List<String> themesList=new ArrayList<>();
        for(int i=3; i<args.length; i++)
        {
            themesList.add(args[i]);
        }
        Student student=new Student(name,startHour,endHour,themesList);
        return student;
    }
}
