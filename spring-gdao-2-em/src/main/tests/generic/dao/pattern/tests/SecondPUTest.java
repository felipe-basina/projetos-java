package generic.dao.pattern.tests;

import generic.dao.context.SpringContainer;
import generic.dao.interfaces.business.TesteService;
import generic.dao.interfaces.business.impl.TesteServiceImpl;
import generic.dao.model.Teste;

import java.util.Date;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SecondPUTest {

	private static SpringContainer context;
	
	private static Logger log = Logger.getLogger(SecondPUTest.class);
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		log.info("log4j inicializado com sucesso!");
		context = SpringContainer.getInstance();
	}

	@Test
	public void gravar() {
		Teste teste = new Teste();
		teste.setData(new Date());
		teste.setName("Nome para teste");
		
		TesteService service = context.getBean(TesteServiceImpl.class);
		service.create(teste);
	}
	
	@Test
	public void recuperar() {
		TesteService service = context.getBean(TesteServiceImpl.class);
		
		for (Teste teste : service.getAll()) {
			System.err.println(teste);
		}
	}
	
	@Test
	public void remover() {
		TesteService service = context.getBean(TesteServiceImpl.class);
		
		for (Teste teste : service.getAll()) {
			service.delete(teste);
		}
	}
}
