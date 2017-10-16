package spring.boot.mvc.jpa.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		return super.preHandle(request, response, handler);
	}
	
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		LOGGER.debug("# Inside interceptor...");
		
		Throwable t = (Throwable) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
		if (t != null) {
			LOGGER.debug("# Exception detected: " + t.getMessage());
		} else {
			LOGGER.debug("# None exception detected!");
		}
	}
	
}
