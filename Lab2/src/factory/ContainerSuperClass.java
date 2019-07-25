package factory;

import domain.Task;

public abstract class ContainerSuperClass implements Container {
    protected int length;
    protected Task[] taskC;
    public abstract Task remove();
    public abstract void add(Task t);

    public int size() {
        return length;
    }
    public boolean isEmpty()
    {
        return length==0;
    }
}
