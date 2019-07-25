package Repository;

import Domain.HasID;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public abstract class AbstractRepo<ID,T extends HasID<ID>> implements Repository<ID,T>{

    private HashMap<ID,T> map;

    public AbstractRepo() {
        map=new HashMap<>();
        //loadFromFile();
    }

    public void save(T t)
    {
        map.put(t.getID(),t);
    }

    public T findOne(ID id)
    {
        return map.get(id);
    }

    @Override
    public Collection<T> findAll(){
        return map.values();
    }
}
