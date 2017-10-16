package br.com.web.ehcache;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class DefaultInterceptor extends HandlerInterceptorAdapter {

	private static Logger LOGGER = LoggerFactory.getLogger(DefaultInterceptor.class);
	
	@Autowired
	private CacheManager cacheManager;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		System.out.println(" # DefaultInterceptor.preHandle ");
		this.checkCache();
		return super.preHandle(request, response, handler);
	}
	
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		LOGGER.debug("# Inside interceptor... " + request.getRequestURI());
		
		Throwable t = (Throwable) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
		if (t != null) {
			LOGGER.debug("# Exception detected: " + t.getMessage());
		} else {
			LOGGER.debug("# None exception detected!");
		}
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println(" # DefaultInterceptor.afterCompletion ");
		this.checkCache();
		super.afterCompletion(request, response, handler, ex);
	}

	private void checkCache() {
		// Verifica os registros que estão no cache
		for (String cacheName : cacheManager.getCacheNames()) {
			System.out.println(" ------> " + cacheName);
			
			Ehcache cache = (Ehcache) cacheManager.getCache(cacheName).getNativeCache();
			if (cache != null) {
				try {
					for (Object key : cache.getKeys()) {
						Element element = cache.get(key);
						if (element != null) {
							System.out.println(" :: key = " + key 
									+ " ---------> " + (Person) element.getObjectValue());
						}
					}
				} catch (Exception ex) {
					LOGGER.error("Erro ao verificar cache: " 
							+ ex.getMessage(), ex);
				}
			}
		}
	}
	
}