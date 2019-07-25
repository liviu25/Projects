package Repository;

import Validator.ValidationException;

/**
 * CRUD operations repository interface
 * @param <ID> - type E must have an attribute of type ID
 * @param <E> - type of entities saved in repository
 */

public interface JRepository<E,ID> {
    /**
     *
     * @param id -the id of the entity to be returned
     * id must not be null
     * @return the entity with the specified id
     * or null - if there is no entity with the given id
     * @throws IllegalArgumentException
     * if id is null.
     */
    E findOne(ID id);

    /**
     *
     * @param e
     * entity must be not null
     * @return null- if the given entity is saved
     * otherwise returns the entity (id already exists)
     * if the entity is not valid
     * @throws IllegalArgumentException
     * if the given entity is null. *
     */
    E save(E e) throws ValidationException;

    /**
     * removes the entity with the specified id
     * @param e
     * id must be not null
     * @return the removed entity or null if there is no entity with the given id
     * @throws IllegalArgumentException
     * if the given id is null.
     */
    E update(E e) throws ValidationException;

    /**
     *
     * @return snumber of elements
     */

    int size();
    /**
     *
     * @return all entities
     */
    Iterable<E> findAll();

    /**
     *
     * @param id
     * entity must not be null
     * @return null - if the entity is updated,
     * otherwise returns the entity - (e.g id does not exist).
     * @throws IllegalArgumentException
     * if the given entity is null
     * if the entity is not valid.
     */
    E delete(ID id);
}
