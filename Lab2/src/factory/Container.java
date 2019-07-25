package factory;

import domain.Task;

public interface Container {
    Task remove();
    void add(Task t);
    int size();
    boolean isEmpty();
}
