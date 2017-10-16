package generic.dao.interfaces.business;

import generic.dao.model.Profile;

import javax.persistence.EntityManager;

public interface ProfileService {
    Profile create(Profile p);
    Profile merge(Profile p);
    EntityManager getEntityManager();
    void delete(Object id);
}
