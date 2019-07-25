package runner;

import domain.Task;

public abstract class AbstractTaskRunner implements  TaskRunner{
    private TaskRunner task;

    public AbstractTaskRunner(TaskRunner task) {
        this.task = task;
    }

    @Override
    public void executeOneTask() {
        task.executeOneTask();
    }

    @Override
    public void executeAll() {
        task.executeAll();
    }

    @Override
    public void addTask(Task t) {
        task.addTask(t);
    }

    @Override
    public boolean hasTasks() {
        return task.hasTasks();
    }
}
