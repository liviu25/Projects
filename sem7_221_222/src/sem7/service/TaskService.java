package sem7.service;

import sem7.model.SortingTask;
import sem7.repository.SortingTaskRepository;
import sem7.utils.Observable;
import sem7.utils.Observer;
import sem7.utils.STEType;
import sem7.utils.SortingTaskEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by grigo on 11/16/16.
 */
public class TaskService implements Observable<SortingTaskEvent> {
    private SortingTaskRepository repo;
    public TaskService(SortingTaskRepository repo){
        this.repo=repo;
    }

    public void addSortingTask(SortingTask task){

        repo.save(task);
        notifyObservers(new SortingTaskEvent(STEType.ADD,task));
    }

    public void deleteSortingTask(SortingTask task){
        repo.delete(task.getId());
        notifyObservers(new SortingTaskEvent(STEType.DELETE, task));
    }
    public Iterable<SortingTask> getAll(){
        return repo.findAll();
    }

    private List<Observer<SortingTaskEvent>> observers=new ArrayList<>();
    @Override
    public void addObserver(Observer<SortingTaskEvent> e) {
        observers.add(e);

    }

    @Override
    public void removeObserver(Observer<SortingTaskEvent> e) {
        observers.remove(e);
    }

    @Override
    public void notifyObservers(SortingTaskEvent t) {
        observers.stream().forEach(x->x.update(t));
    }

    public void updateSortingTask(SortingTask oldTask, SortingTask newTask) {
        repo.update(oldTask.getId(),newTask);
        notifyObservers(new SortingTaskEvent(STEType.UPDATE,newTask,oldTask));
    }
}
