package br.com.spring.jdbc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringContext {

	private final ApplicationContext context;
	private static SpringContext ist;

	private final String[] springFiles = { "classpath:resource/application-context.xml" };

	private SpringContext() {
		context = new ClassPathXmlApplicationContext(springFiles);
		ist = this;
	}

	public static SpringContext getInstance() {
		if (ist == null) {
			ist = new SpringContext();
		}
		return ist;
	}

	@SuppressWarnings("unchecked")
	public <T> T getBean(String beanName) {
		return (T) context.getBean(beanName);
	}

	public <T> T getBean(Class<T> bean) {
		return context.getBean(bean);
	}

	public <T> T getBean(Class<T> bean, Object... args) {
		return context.getBean(bean, args);
	}
}
