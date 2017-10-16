package generic.dao.interfaces.dao.impl;

import generic.dao.impl.GenericDaoImpl;
import generic.dao.interfaces.dao.UserDao;
import generic.dao.model.Person;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserDaoImpl extends GenericDaoImpl<Person> implements UserDao {
	
	@SuppressWarnings("unchecked")
	@Transactional
	public Person loadUserByName(String name) {
		Query consulta = em.createQuery("from Person p JOIN FETCH p.phones WHERE p.name LIKE :name");
		consulta.setParameter("name", "%" + name + "%");
		
		List<Person> persons = (ArrayList<Person>) consulta.getResultList();
		if (persons.size() > 0) {
			return (Person) consulta.getResultList().get(0);	
		}
		return null;	
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Person> getAllLimited(String name, int init, int max) {
		Query consulta = em.createNamedQuery("getListOfPeopleByName");
		
		if (init > 0) {
			consulta.setFirstResult(init);	
		}
		consulta.setMaxResults(max);
		consulta.setParameter("name", "%" + "Nome" + "%");
		
		return (ArrayList<Person>) consulta.getResultList();	
	}
	
	@Override
	public Person getPersonById(int id) {
		Query consulta = em.createNamedQuery("getPeopleById");
		consulta.setParameter("id", id);
		
		return (Person) consulta.getResultList().get(0);
	}
}

