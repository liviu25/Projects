package Repository;

import Model.HasID;

public interface JRepository<E extends HasID<ID>,ID> {
    E findOne(ID id);
    E save(E e);
    void delete(ID id);
    int size();
    Iterable<E> findAll();
}
