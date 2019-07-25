package Repository;

import Validator.*;

import java.util.HashMap;

public abstract class AbstractJRepository<E extends HasID<ID>,ID> implements JRepository<E,ID> {
    private Validator<E> v;
    private HashMap<ID,E> map;

    public AbstractJRepository(Validator<E> v) {
        this.v = v;
        map=new HashMap<>();
    }


    @Override
    public E findOne(ID id){
        return map.get(id);
    }


    @Override
    public E save(E e) throws ValidationException{
        v.validate(e);
        E elem = findOne(e.getID());

        map.put(e.getID(), e);
        return elem;
    }


    @Override
    public E update(E e) throws ValidationException{
        v.validate(e);
        E elem = map.get(e.getID());
        if (elem != null) {
            map.put(e.getID(), e);
            return null;
        }
        map.put(e.getID(), e);
        return elem;
    }


    @Override
    public int size() {
        return map.size();
    }


    @Override
    public Iterable<E> findAll() {
        return map.values();
    }


    @Override
    public E delete(ID id) {
        E elem=map.get(id);
        if(elem==null)
        {
            return null;
        }
        map.remove(id);
        return elem;

    }
}
