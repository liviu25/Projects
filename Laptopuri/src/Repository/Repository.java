package Repository;

import Domain.HasID;

import java.util.Collection;

public interface Repository<ID,T> {
    Collection<T> findAll();

}
