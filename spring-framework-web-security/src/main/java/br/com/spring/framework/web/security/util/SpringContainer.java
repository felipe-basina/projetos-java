/**
 * 
 */
package br.com.spring.framework.web.security.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @author 92035169
 *
 */
public class SpringContainer {

	private final ApplicationContext context;
	private static SpringContainer ist;

	private String[] springFiles = { "classpath:resources/spring-files.xml" };

	private SpringContainer() {
		context = new FileSystemXmlApplicationContext(springFiles);
		ist = this;
	}

	public static SpringContainer getInstance() {
		if (ist == null) {
			ist = new SpringContainer();
		}
		return ist;
	}

	public <T> T getBean(Class<T> bean) {
		return context.getBean(bean);
	}

}
