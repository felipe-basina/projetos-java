package spring.boot.mvc.jpa.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
@ComponentScan(basePackages = "spring.boot.mvc.jpa")
public class CacheeConfig {

	@Bean
	public CacheManager cacheManager() {
		return new ConcurrentMapCacheManager("findByNomeCache");
	}
}
