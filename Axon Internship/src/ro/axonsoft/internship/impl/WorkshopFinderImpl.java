package ro.axonsoft.internship.impl;

import ro.axonsoft.internship.api.SearchResult;
import ro.axonsoft.internship.api.StudentDescriptor;
import ro.axonsoft.internship.api.WorkshopDescriptor;
import ro.axonsoft.internship.api.WorkshopFinder;

import java.util.*;
import java.util.stream.Collectors;

public class WorkshopFinderImpl implements WorkshopFinder {
    private List<WorkshopDescriptor> workshopDescriptorList;

    public WorkshopFinderImpl(List<WorkshopDescriptor> workshopDescriptorList) {
        this.workshopDescriptorList = workshopDescriptorList;
    }

    @Override
    public SearchResult getWorkshops(StudentDescriptor studentDescriptor) {
        List<WorkshopDescriptor> workshops = workshopDescriptorList.stream()
                .filter(workshopDescriptor ->
                        workshopDescriptor.getStartHour().isAfter(studentDescriptor.getStartHour()) ||
                        workshopDescriptor.getStartHour().equals(studentDescriptor.getStartHour()) ||
                        workshopDescriptor.getStartHour().plusMinutes(workshopDescriptor.getDuration()).isBefore(studentDescriptor.getEndHour()) ||
                        workshopDescriptor.getStartHour().plusMinutes(workshopDescriptor.getDuration()).equals(studentDescriptor.getEndHour()))
                .collect(Collectors.toList());


        if(!studentDescriptor.getThemesList().isEmpty()) {
            workshops = workshops.stream()
                    .filter(workshopDescriptor -> !studentDescriptor.getThemesList().isEmpty())
                    .filter(workshopDescriptor -> studentDescriptor.getThemesList().contains(workshopDescriptor.getTheme()))
                    .collect(Collectors.toList());
        }

        Queue<String> queue=new LinkedList<>();
        Map<String,List<String>> tree=new HashMap<>();
        String start="";
        for (int i = 0; i < workshops.size(); i++) {
            start+=0;
        }
        queue.add(start);
        int maxLength=-1;
        String best="";

        while (!queue.isEmpty())
        {
            String currentNode = queue.poll();
            int lastIndexOf1 = currentNode.lastIndexOf('1');
            List<String> children=new LinkedList<>();
            for (int i=lastIndexOf1+1 ; i<workshops.size(); i++){
                if(lastIndexOf1!=-1){
                    WorkshopDescriptor lastWorkshop = workshops.get(lastIndexOf1);
                    if(lastWorkshop.getHall()!=workshops.get(i).getHall())
                    {
                        if(lastWorkshop.getStartHour().plusMinutes(lastWorkshop.getDuration()).plusMinutes(10)
                                .isBefore(workshops.get(i).getStartHour()))
                        {
                            StringBuilder stringBuilder=new StringBuilder(currentNode);
                            stringBuilder.setCharAt(i,'1');
                            children.add(stringBuilder.toString());
                        }
                    }
                    else
                    if(lastWorkshop.getStartHour().plusMinutes(lastWorkshop.getDuration())
                            .isBefore(workshops.get(i).getStartHour()))
                    {
                        StringBuilder stringBuilder=new StringBuilder(currentNode);
                        stringBuilder.setCharAt(i,'1');
                        children.add(stringBuilder.toString());
                    }
                }
                else
                {
                    StringBuilder stringBuilder=new StringBuilder(currentNode);
                    stringBuilder.setCharAt(i,'1');
                    children.add(stringBuilder.toString());
                }
            }
            if(!children.isEmpty()) {
                tree.put(currentNode, children);
                queue.addAll(children);
            }

            int count = (int) currentNode.chars().filter(ch -> ch == '1').count();
            if(count>maxLength){
                best=currentNode;
            }
        }

        List<WorkshopDescriptor> rez=new LinkedList<>();
        for (int i = 0; i < best.length(); i++) {
            if(best.charAt(i)=='1')
            {
                rez.add(workshops.get(i));
            }
        }

        return new SearchResultImpl(studentDescriptor,rez);
    }
}
