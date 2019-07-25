package domain;

public class SortingTask extends Task{
    private int v[];
    AbstractSorter sorter;


    public SortingTask(String taskID, String descriere,int v[], SortingStrategy s) {
        super(taskID,descriere);
        if(s==SortingStrategy.BubbleSort)
            sorter =new BubbleSort(v);
        else if(s==SortingStrategy.QuickSort)
            sorter =new QuickSort(v);
        this.v=v;
    }


    public void execute() {
        //domain.QuickSort bs=new domain.QuickSort(v);
        sorter.sort();
        for(int i=0;i<v.length; i++)
        {
            System.out.print(v[i]+" ");
        }
    }

}
