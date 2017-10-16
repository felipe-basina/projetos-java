package generic.dao.interfaces.business;

import generic.dao.model.Person;

import java.util.List;

import javax.persistence.EntityManager;

public interface UserService {
    Person create(Person t);
    void delete(Object id);
    Person find(Object id);
    Person update(Person t);
    List<Person> getAll();
    Person loadUserByName(String name);
    EntityManager getEntityManager();
    void getAllLimited(String name, int max);
	Person getPersonById(int id);
	void deleteLimitedByMax(String filterName, int max);
}
