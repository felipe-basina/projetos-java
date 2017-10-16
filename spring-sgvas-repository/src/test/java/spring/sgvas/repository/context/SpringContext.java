package spring.sgvas.repository.context;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringContext {

	private static AbstractApplicationContext context;
	
	private static final String[] pathConfigurationFiles = { "application-context.xml" };
	
	private static SpringContext instance;
	
	private SpringContext() {
		context = new ClassPathXmlApplicationContext(pathConfigurationFiles);
		instance = this;
	}
	
	public static SpringContext getInstance() {
		if (instance == null) {
			instance = new SpringContext();
		}
		return instance;
	}

	public <T> T getBean(Class<T> bean) {
		return context.getBean(bean);
	}
	
}
