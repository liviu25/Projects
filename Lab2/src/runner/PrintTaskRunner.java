package runner;

import java.time.LocalDateTime;

public class PrintTaskRunner extends AbstractTaskRunner {
    public PrintTaskRunner(TaskRunner task) {
        super(task);
    }

    @Override
    public void executeOneTask() {
        super.executeOneTask();
        System.out.println(LocalDateTime.now());
    }

    @Override
    public void executeAll() {
        while(super.hasTasks()){
            executeOneTask();
        }

    }
}
