package generic.dao.pattern.tests;

import generic.dao.context.SpringContainer;
import generic.dao.interfaces.business.UserService;
import generic.dao.interfaces.business.impl.UserServiceImpl;
import generic.dao.model.Person;
import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

public class ConsultarGenericTest {
	
	private static SpringContainer context;
	
	private static Logger log = Logger.getLogger(ConsultarGenericTest.class);
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = SpringContainer.getInstance();
	}
	
	@Test
	public void criar() {
		log.info("Criando Objeto!");
		
		UserService service = context.getBean(UserServiceImpl.class);
		
		Person person = new Person();
		person.setCity("Teste 01");
		person.setAddress("Rua Teste 01");
		person.setName("Teste000");
		
		service.create(person);
	}
	
	@Test
	public void atualizar() {
		log.info("Atualizando Objeto!");
		
		UserService service = context.getBean(UserServiceImpl.class);
		
		Person person = service.loadUserByName("Teste000");		
		person.setAddress("Mudanca Teste 01");
		
		service.update(person);
	}
	
	@Test
	public void remover() {
		log.info("Removendo Objeto!");
		
		UserService service = context.getBean(UserServiceImpl.class);
		
		Person person = service.loadUserByName("Teste000");
		
		service.delete(person.getId());
	}
	
	@Test
	public void recuperar() {
		log.info("Conferindo!");
		
		UserService service = context.getBean(UserServiceImpl.class);
		
		Person person = service.loadUserByName("Teste000");
		Assert.assertTrue(person == null);
	}
}
