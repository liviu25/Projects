package tasks.repository;

import tasks.model.SortingTask;

/**
 * Created by grigo on 11/14/16.
 */
public class SortingTaskRepository extends AbstractRepository<Integer, SortingTask> {

    public SortingTaskRepository(Validator<SortingTask> val){
        super(val);
    }

}
