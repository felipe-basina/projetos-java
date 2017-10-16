package generic.dao.pattern.tests;

import generic.dao.context.SpringContainer;
import generic.dao.interfaces.business.PhoneService;
import generic.dao.interfaces.business.impl.PhoneServiceImpl;
import generic.dao.model.Phone;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

public class ManyToOneTest {

	private static SpringContainer context;
	
	private static Logger log = Logger.getLogger(OneToManyTest.class);
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = SpringContainer.getInstance();
		
		log.info("Contexto Inicializado!");
	}

	@Test
	public void recuperarPorCodigoTelefone() {
		PhoneService service = context.getBean(PhoneServiceImpl.class);
		
		Query query = service.getEntityManager().createNamedQuery("getPeopleFromPhone");
		query.setParameter("id", 99978);
		
		Phone phone = (Phone) query.getResultList().get(0);
		System.out.println(phone);
	}

}
