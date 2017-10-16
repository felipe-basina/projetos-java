package generic.dao.pattern.tests;

import org.apache.log4j.Logger;
import org.junit.Test;

public class Log4jTest {

	private static final Logger log = Logger.getLogger(Log4jTest.class);
	
	@Test
	public void teste() {
		log.info("teste!");
	}
}
