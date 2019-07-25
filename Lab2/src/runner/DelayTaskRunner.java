package runner;

public class DelayTaskRunner extends AbstractTaskRunner {
    public DelayTaskRunner(TaskRunner task) {
        super(task);
    }

    @Override
    public void executeOneTask() {
        try {
            Thread.sleep(3000);
            super.executeOneTask();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void executeAll() {
        while(super.hasTasks())
        {
            executeOneTask();
        }
    }
}
