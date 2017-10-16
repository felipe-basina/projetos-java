package generic.dao.pattern.tests;

import generic.dao.context.SpringContainer;
import generic.dao.interfaces.business.UserService;
import generic.dao.interfaces.business.impl.UserServiceImpl;
import generic.dao.model.Person;
import generic.dao.model.Phone;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

public class OneToManyTest {

	private static SpringContainer context;
	
	private static Logger log = Logger.getLogger(OneToManyTest.class);
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		log.info("log4j inicializado com sucesso!");
		context = SpringContainer.getInstance();
	}

	@Test
	public void persistir() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - kk:mm:ss");
		
		log.info("Processo Iniciado em [" + sdf.format(new Date()) + "]");
		
		UserService service = context.getBean(UserServiceImpl.class);
		
		int max = 1;
		for (int total = 1; total <= max; total++) {
			Person p = new Person();
			p.setName(total + "Nome Sobrenome Ultimonome " + total);
			p.setCity("Cidade cidade");
			p.setAddress("Endereco Rua Numero CEP");
			
			Set<Phone> phs = new HashSet<Phone>();
			for (int indice = 1; indice <= 10; indice++) {
				Phone ph = new Phone();
				ph.setPhone(total + "ddd-pppp-fff" + indice);
				ph.setPerson(p);
				
				phs.add(ph);
			}
			p.setPhones(phs);
			
			service.create(p);
		}
		log.info("Processo Finalizado em [" + sdf.format(new Date()) + "]");
	}

	@Test
	public void recuperarValores() {
		UserService service = context.getBean(UserServiceImpl.class);
		
		Person person = service.loadUserByName("1Nome");
		System.out.println(person);
	}
	
	@Test
	public void recuperarPorId() {
		UserService service = context.getBean(UserServiceImpl.class);
		
		try {
			Person person = (Person) service.getEntityManager().createNamedQuery("getPeopleById").
					setParameter("id", 206).getResultList().get(0);
			System.out.println(person);
		} catch (Exception ex) {
			System.err.println(ex);
		}
	}
	
	@Test
	public void recuperarValoresNamedQuery() {
		UserService service = context.getBean(UserServiceImpl.class);
		
		Person person = (Person) service.getEntityManager().createNamedQuery("getListOfPeopleByNameJoinFetch").
				setParameter("name", "%" + "Nome" + "%").getResultList().get(0);
		System.out.println(person);
	}
	
	@Test
	public void remover() {
		UserService service = context.getBean(UserServiceImpl.class);
		
		Person person = service.loadUserByName("Nome");
		service.delete(person.getId());
	}
	
	@Test
	public void loadLimited() {
		UserService service = context.getBean(UserServiceImpl.class);
		service.getAllLimited("Nome", 100);
	}
	
	@Test
	public void deleteLimitedByMax() {
		UserService service = context.getBean(UserServiceImpl.class);
		service.deleteLimitedByMax("Nome", 250);
	}
	
	@Test
	public void getId() {
		UserService service = context.getBean(UserServiceImpl.class);
		
		Person p = new Person();
		p.setName("Nome Sobrenome Ultimonome para ID");
		p.setCity("Cidade cidade para ID");
		p.setAddress("Endereco Rua Numero CEP para ID");
		
		service.create(p);
		System.out.println("Person: " + p);
		
		Integer id = (Integer) service.getEntityManager().getEntityManagerFactory().getPersistenceUnitUtil()
				.getIdentifier(p);
		System.out.println("ID: " + id);
	}
	
	@Test
	public void removerPorId() {
		UserService service = context.getBean(UserServiceImpl.class);
		service.delete(1);
	}
}
