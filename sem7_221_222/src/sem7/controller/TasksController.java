package sem7.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sem7.model.SortingAlgorithm;
import sem7.model.SortingOrder;
import sem7.model.SortingTask;
import sem7.service.TaskService;
import sem7.utils.Observer;
import sem7.utils.SortingTaskEvent;

/**
 * Created by grigo on 11/14/16.
 */
public class TasksController implements Observer<SortingTaskEvent>{
    private TaskService service;
    private ObservableList<SortingTask> tasksModel;
    public TasksController(TaskService service){
        this.service=service;
        service.addObserver(this);
        tasksModel= FXCollections.observableArrayList();
        populateList();
    }

    private void populateList(){
        Iterable<SortingTask> tasks=service.getAll();
        tasks.forEach(x->tasksModel.add(x));
    }
    public void addSortingTask(int id, String desc, SortingOrder order, SortingAlgorithm alg, int nrElem){
        SortingTask task=new SortingTask(id,desc,alg,order,nrElem);
        service.addSortingTask(task);

    }

    public void deleteTask(SortingTask task){

        service.deleteSortingTask(task);
    }

    public ObservableList<SortingTask> getTasksModel(){

        return tasksModel;
    }

    @Override
    public void update(SortingTaskEvent sortingTaskEvent) {
        switch (sortingTaskEvent.getType()){
            case ADD:{ tasksModel.add(sortingTaskEvent.getData()); break;}
            case DELETE:{tasksModel.remove(sortingTaskEvent.getData()); break;}
            case UPDATE:{ tasksModel.remove(sortingTaskEvent.getOldData());
                tasksModel.add(sortingTaskEvent.getData()); break;}
        }
    }

    public void updateTask(SortingTask oldTask, int idVal, String desc, SortingOrder orderV, SortingAlgorithm algo, int nrElemVal) {
        SortingTask newTask=new SortingTask(idVal,desc,algo,orderV,nrElemVal);
        service.updateSortingTask(oldTask,newTask);
    }
}
