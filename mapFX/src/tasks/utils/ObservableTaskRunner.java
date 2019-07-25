package tasks.utils;



import tasks.model.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by grigo on 11/8/16.
 */
public class ObservableTaskRunner  implements ITaskRunner, Observable<TaskEvent>{
    protected Container container;

    public ObservableTaskRunner(Container container) {
        this.container = container;
    }

    protected List<Observer<TaskEvent>> observers=new ArrayList<>();

    @Override
    public void addObserver(Observer<TaskEvent> e) {
        observers.add(e);
    }

    @Override
    public void removeObserver(Observer<TaskEvent> e) {
        observers.remove(e);
    }

    @Override
    public  void notifyObservers(TaskEvent t) {
        for(Observer<TaskEvent> ovs:observers)
            ovs.update(t);

    }


    @Override
    public void executeOneTask() {
        Task task=container.remove();
        notifyObservers(new TaskEvent(TaskEventType.StartingTaskExecution, task));
        task.execute();
        notifyObservers(new TaskEvent(TaskEventType.TaskExecutionCompleted,task));
    }

    @Override
    public void executeAll() {
        while(!container.isEmpty())
            executeOneTask();
    }

    @Override
    public void addTask(Task t) {
        container.add(t);
    }
}
