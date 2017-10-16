package br.com.sample.without.spring.boot;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class SpringContext {

	private final ApplicationContext context;
	private static SpringContext ist;

	private String[] springFiles = { "classpath:spring-config.xml" };

	private SpringContext() {
		context = new FileSystemXmlApplicationContext(springFiles);
		ist = this;
	}

	public static SpringContext getInstance() {
		if (ist == null) {
			ist = new SpringContext();
		}
		return ist;
	}

	public <T> T getBean(Class<T> bean) {
		return context.getBean(bean);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getBean(String beanName) {
		return (T) context.getBean(beanName);
	}
}
