package spring.jpa.query.methods;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.jpa.query.methods.service.BaseService;
import spring.jpa.query.methods.service.IBaseService;

/**
 * Unit test for simple App.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RecuperarRemoverPorNativeQueryRecuperarUltimoIdTest {

	private static IBaseService service;

	private static AbstractApplicationContext context;

	@BeforeClass
	public static void setUp() {
		context = new ClassPathXmlApplicationContext("application-context.xml");
		service = context.getBean(BaseService.class);
	}

	@AfterClass
	public static void setDown() {
		context.close();
	}

	@Test
	public void getAndDeleteFromNativeQuery() {
		long id = service.getIdFromEntityManager();
		System.out.println(" #### id :: " + id);

		if (id > 0) {
			service.deleteById(id);
		}
	}

	@Test
	public void getLastId() {
		System.out.println(" ### last id :: " + service.getLastId());
	}
}
