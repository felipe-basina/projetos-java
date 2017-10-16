package spring.boot.mvc.jpa.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import spring.boot.mvc.jpa.interceptor.DefaultInterceptor;

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

	@Override
	  public void addInterceptors(InterceptorRegistry registry) {
	    registry.addInterceptor(new DefaultInterceptor()); 
	  }
}
