package br.com.web.ehcache;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableCaching
@EnableWebMvc
@PropertySource(value = { "classpath:application.properties" })
@ComponentScan(basePackages = {"br.com.web.ehcache"})
public class ApplicationConfiguration extends WebMvcConfigurerAdapter {

	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	@Bean
	public CacheManager cacheManager() {
		return new EhCacheCacheManager(this.ehCacheCacheManager().getObject());
	}

	@Bean
	public EhCacheManagerFactoryBean ehCacheCacheManager() {
		EhCacheManagerFactoryBean cmfb = new EhCacheManagerFactoryBean();
		cmfb.setConfigLocation(new ClassPathResource("ehcache.xml"));
		cmfb.setShared(true);
		return cmfb;
	}
	
	@Override
	  public void addInterceptors(InterceptorRegistry registry) {
	    registry.addInterceptor(this.defaultInterceptor()); 
	  }

	@Bean
	public DefaultInterceptor defaultInterceptor() {
		return new DefaultInterceptor();
	}
	
}