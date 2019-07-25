public interface JRepository<E,ID> {
    E findOne(ID id);
    E save(E e);
    E update(E e);
    int size();
    Iterable<E> findAll();
    E delete(ID id);
}
