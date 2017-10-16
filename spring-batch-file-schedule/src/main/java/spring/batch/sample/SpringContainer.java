package spring.batch.sample;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class SpringContainer {

    private final ApplicationContext context;
    private static SpringContainer ist;

    private String[] springFiles = { "classpath:spring/batch/config/spring-files.xml" };

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

    /**
     * Retorna o bean através do nome específico
     * 
     * @param <T>
     * @param beanName
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> T getBean(String beanName) {
	return (T) context.getBean(beanName);
    }
}
