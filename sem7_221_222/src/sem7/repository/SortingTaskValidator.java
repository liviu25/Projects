package sem7.repository;

import sem7.model.SortingAlgorithm;
import sem7.model.SortingTask;

/**
 * Created by grigo on 11/14/16.
 */
public class SortingTaskValidator implements Validator<SortingTask> {
    @Override
    public void validate(SortingTask entity) throws ValidationException {
        StringBuffer msg=new StringBuffer();
        if (entity.getId()<0)
            msg.append("Id-ul nu poate fi negativ: " + entity.getId());
        if (msg.length()>0)
            throw new ValidationException(msg.toString());
    }
}

