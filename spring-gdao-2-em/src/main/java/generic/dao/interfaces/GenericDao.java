package generic.dao.interfaces;

import java.util.List;

import javax.persistence.EntityManager;

public interface GenericDao<T> {
    T create(T t);
    void delete(Object id);
    T find(Object id);
    T update(T t);
    List<T> getAll();
    EntityManager getEntityManager();
}
