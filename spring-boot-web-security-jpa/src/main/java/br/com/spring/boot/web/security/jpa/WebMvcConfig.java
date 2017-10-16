package br.com.spring.boot.web.security.jpa;

import java.util.Locale;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Autowired
	private MessageSource message;

	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/home").setViewName("home");
		registry.addViewController("/").setViewName("home");
		registry.addViewController("/hello").setViewName("hello");
		registry.addViewController("/login").setViewName("login");
	}

	@Bean
	public MessageSource messageSource() {
		String[] propriedades = { "classpath:application" };

		ReloadableResourceBundleMessageSource reloadRes = new ReloadableResourceBundleMessageSource();
		reloadRes.setBasenames(propriedades);
		reloadRes.setDefaultEncoding("ISO-8859-1");
		return reloadRes;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan(new String[] { "br.com.spring.boot.web.security.jpa" });

		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(this.additionalProperties());

		return em;
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource
				.setDriverClassName(message.getMessage(
						"spring.datasource.driverClassName", null,
						Locale.getDefault()));
		dataSource.setUrl(message.getMessage("spring.datasource.url", null,
				Locale.getDefault()));
		dataSource.setUsername(message.getMessage("spring.datasource.username",
				null, Locale.getDefault()));
		dataSource.setPassword(message.getMessage("spring.datasource.password",
				null, Locale.getDefault()));
		return dataSource;
	}

	@Bean
	public PlatformTransactionManager transactionManager(
			EntityManagerFactory emf) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);

		return transactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	private Properties additionalProperties() {
		Properties properties = new Properties();
		properties.setProperty(
				message.getMessage("spring.jpa.hibernate.hbm2ddl.auto", null,
						Locale.getDefault()), message.getMessage(
						"spring.jpa.hibernate.ddl-auto", null,
						Locale.getDefault()));
		properties.setProperty(message.getMessage(
				"spring.jpa.hibernate.dialect", null, Locale.getDefault()),
				message.getMessage("spring.jpa.hibernate.dialect.value", null,
						Locale.getDefault()));
		properties.setProperty(message.getMessage(
				"spring.jpa.hibernate.show_sql", null, Locale.getDefault()),
				message.getMessage("spring.jpa.hibernate.show_sql.value", null,
						Locale.getDefault()));
		return properties;
	}
}