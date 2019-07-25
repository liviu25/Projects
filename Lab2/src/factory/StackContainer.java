package factory;

import domain.Task;

public class StackContainer extends ContainerSuperClass {
    //private int length;
    //private domain.Task[] taskC;

    public StackContainer() {
        length=0;
        taskC=new Task[20];
    }

    @Override
    public Task remove() {
        Task t=taskC[length-1];
        taskC[length]=null;
        length--;
        return t;
    }

    @Override
    public void add(Task t) {
        taskC[length]=t;
        length++;
    }

}
