package spring.boot.mvc.jpa.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class DefaultInterceptor extends HandlerInterceptorAdapter {

	private static Logger LOGGER = LoggerFactory.getLogger(DefaultInterceptor.class);
	
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
