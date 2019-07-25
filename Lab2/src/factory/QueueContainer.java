package factory;

import domain.Task;

public class QueueContainer extends ContainerSuperClass {


    //private int length;
    //private domain.Task[] taskC;

    public QueueContainer() {
        length=0;
        taskC=new Task[20];
    }

    @Override
    public Task remove() {
        Task t=taskC[0];
        length--;
        for(int i=0; i<length; i++) {
            taskC[i] = taskC[i+1];
        }
        return t;
    }

    @Override
    public void add(Task t) {
        taskC[length]=t;
        length++;
    }

}
