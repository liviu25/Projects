package factory;

public class TaskContainerFactory implements Factory {

    private static TaskContainerFactory instance;
    @Override
    public Container createContainer(Strategy s) {
        if(s==Strategy.LIFO)
            return new StackContainer();
        else if(s==Strategy.FIFO)
            return new QueueContainer();
        return null;
    }
    private TaskContainerFactory(){

    }
    public static TaskContainerFactory getInstance(){
        if(instance==null)
        {
            instance=new TaskContainerFactory();
        }
        return instance;
    }
}
