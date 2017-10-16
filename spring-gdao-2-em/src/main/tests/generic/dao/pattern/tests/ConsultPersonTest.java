package generic.dao.pattern.tests;

import generic.dao.context.SpringContainer;
import generic.dao.interfaces.business.UserService;
import generic.dao.interfaces.business.impl.UserServiceImpl;
import generic.dao.model.Person;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

public class ConsultPersonTest {

	private static SpringContainer container;
	
	private static Logger log = Logger.getLogger(ConsultPersonTest.class);
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		log.info("log4j Inicializado!");
		
		container = SpringContainer.getInstance();
	}

	@Test
	public void listAll() {		
		UserService service = container.getBean(UserServiceImpl.class);
		
		List<Person> persons = service.getAll();
		for (Person person : persons) {
			System.out.println(person);
		}
	}
}
