package spring.boot.mvc.jpa.exception;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ControllerAdviceForException {

    private static Logger LOGGER = LoggerFactory.getLogger(ControllerAdviceForException.class);

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception ex) {
    	LOGGER.debug("# Handle exception: " + ex.getMessage(), ex);
    	return new ModelAndView("500");
    }
}
