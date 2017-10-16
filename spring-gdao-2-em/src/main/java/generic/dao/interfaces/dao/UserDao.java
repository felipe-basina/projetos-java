package generic.dao.interfaces.dao;

import generic.dao.interfaces.GenericDao;
import generic.dao.model.Person;

import java.util.List;

public interface UserDao extends GenericDao<Person> {

	Person loadUserByName(String name);
	Person getPersonById(int id);
	List<Person> getAllLimited(String name, int init, int max);
}
