package spring.boot.mvc.jpa.security.config;

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
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

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

	@Bean
	public RequestContextListener requestContextListener() {
		return new RequestContextListener();
	}

	@Bean
	public UrlBasedViewResolver tilesViewResolver() {

		UrlBasedViewResolver tilesViewResolver = new UrlBasedViewResolver();
		tilesViewResolver.setViewClass(TilesView.class);
		tilesViewResolver.setOrder(0);
		return tilesViewResolver;
	}

	@Bean
	public TilesConfigurer tilesConfigurer() {
		String[] definitions = new String[] { "/WEB-INF/layouts/tiles.xml" };

		TilesConfigurer tilesConfigurer = new TilesConfigurer();
		tilesConfigurer.setDefinitions(definitions);
		return tilesConfigurer;
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource reloadRes = new ReloadableResourceBundleMessageSource();
		reloadRes.setBasename("WEB-INF/messages/messages_pt_BR");
		reloadRes.setDefaultEncoding("ISO-8859-1");
		return reloadRes;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan(new String[] { "spring.boot.mvc.jpa.security" });

		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(this.additionalProperties());

		return em;
	}

	@Bean
	public DataSource dataSource() {
		/** Recuperando informação para data source a partir de JNDI **/
		/*final JndiDataSourceLookup lookup = new JndiDataSourceLookup();
		lookup.setResourceRef(true);
		
		DataSource ds = lookup.getDataSource(message.getMessage(
				"spring.datasource.jndi", null, Locale.getDefault())); 		
		return ds;*/
		
		/** Definindo configurações para conexão direta com a base de dados **/
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

	Properties additionalProperties() {
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