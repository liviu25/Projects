package runner;

import domain.Task;
import factory.Container;
import factory.Strategy;
import factory.TaskContainerFactory;

public class StrategyTaskRunner implements TaskRunner {
    private Container container;
    public StrategyTaskRunner(Strategy s) {
        container = TaskContainerFactory.getInstance().createContainer(s);
    }

    @Override
    public void executeOneTask() {
        if(!container.isEmpty())
        {
            Task t=container.remove();
            t.execute();
        }
    }

    @Override
    public void executeAll() {
        while(!container.isEmpty())
        {
            executeOneTask();
        }
    }

    @Override
    public void addTask(Task t) {
        container.add(t);
    }

    @Override
    public boolean hasTasks() {
        return !container.isEmpty();
    }
}
