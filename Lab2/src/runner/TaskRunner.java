package runner;


import domain.Task;

public interface TaskRunner {
    public void executeOneTask();
    public void executeAll();
    public void addTask(Task t);
    public boolean hasTasks();
}
