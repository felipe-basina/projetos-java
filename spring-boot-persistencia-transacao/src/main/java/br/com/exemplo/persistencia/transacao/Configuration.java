package br.com.exemplo.persistencia.transacao;

import java.util.Locale;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@org.springframework.context.annotation.Configuration
public class Configuration {

	@Autowired
	private MessageSource message;

	@Bean
	public MessageSource messageSource() {
		String[] propriedades = { "classpath:data-source-config" };

		ReloadableResourceBundleMessageSource reloadRes = new ReloadableResourceBundleMessageSource();
		reloadRes.setBasenames(propriedades);
		reloadRes.setDefaultEncoding("ISO-8859-1");
		return reloadRes;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(this.dataSource());
		em.setPackagesToScan(new String[] { "br.com.exemplo.persistencia.transacao" });

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
		return properties;
	}
}
