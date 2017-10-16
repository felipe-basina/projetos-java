package generic.dao.interfaces.business.impl;

import generic.dao.interfaces.business.PhoneService;
import generic.dao.interfaces.business.UserService;
import generic.dao.interfaces.dao.UserDao;
import generic.dao.model.Person;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("userServiceImpl")
@Scope("prototype")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao uDao;
	
	@Autowired
	private PhoneService pService;
	
    public Person create(Person p) {
    	return this.uDao.create(p);
    }
    
    public void delete(Object id) {
    	this.uDao.delete(id);
    }
    
    public Person find(Object id) {
    	return this.uDao.find(id);
    }
    
    public Person update(Person p) {
    	return this.uDao.update(p);
    }
    
    public Person loadUserByName(String name) {
    	return this.uDao.loadUserByName(name);
    }
    
    public List<Person> getAll() {
    	return this.uDao.getAll();
    }
    
    @Override
    public EntityManager getEntityManager() {
    	return this.uDao.getEntityManager();
    }
    
    public void getAllLimited(String name, int max) {
    	int init = 0;
    	
    	List<Person> persons;
    	while ((persons = this.uDao.getAllLimited(name, init, max)).size() > 0) {
    		
    		System.out.println("Total Registros: " + persons.size() + "\nValor Indice: " + init);
    		//System.out.println(persons.get(0).getId());
    		System.out.println("---------");
    		
    		init += persons.size();
    	}
    }
    
    @Override
    public Person getPersonById(int id) {
    	return this.uDao.getPersonById(id);
    }
    
    public void deleteLimitedByMax(String filterName, int max) {
    	int init = 0;
    	
    	List<Person> persons;
    	while ((persons = this.uDao.getAllLimited(filterName, init, max)).size() > 0) {    		
    		for (Person person : persons) {
    			int id = person.getId();
    					
    			this.pService.deletePhoneByUserId(id);
    			this.uDao.delete(id);
    		}
    	}
    }
}
