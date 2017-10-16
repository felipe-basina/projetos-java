package br.com.spring.testes;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class SpringContext {

	private final ApplicationContext context;
	private static SpringContext ist;

	private String[] springFiles = { "classpath:resources/spring-files.xml" };

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

}
