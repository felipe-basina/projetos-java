package spring.boot.mvc.bootstrap.handlers;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import spring.boot.mvc.bootstrap.controller.UserRestController;
import spring.boot.mvc.bootstrap.exception.EmailAlreadyInUseException;
import spring.boot.mvc.bootstrap.exception.ExceptionJSONInfo;
import spring.boot.mvc.bootstrap.exception.UserNotFoundException;

@ControllerAdvice
public class ExceptionHandlers {
	
	private static Logger log = LoggerFactory.getLogger(UserRestController.class);
	
	/**************************** 
	 * UserRestController
	 *****************************/
	
	@ExceptionHandler(value = UserNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public @ResponseBody ExceptionJSONInfo handleUserNotFoundException(
			HttpServletRequest request, Exception ex) {
		log.debug("\n\tEntrou no handler [handleUserNotFoundException]....\n");
		ExceptionJSONInfo response = new ExceptionJSONInfo();
		response.setUrl(request.getRequestURL().toString());
		response.setMessage(ex.getMessage());
		return response;
	}
	
	@ExceptionHandler(value = EmailAlreadyInUseException.class)
	@ResponseStatus(value = HttpStatus.CONFLICT)
	public @ResponseBody ExceptionJSONInfo handleEmailAlreadyInUseException(
			HttpServletRequest request, Exception ex) {
		log.debug("\n\tEntrou no handler [handleEmailAlreadyInUseException]....\n");
		ExceptionJSONInfo response = new ExceptionJSONInfo();
		response.setUrl(request.getRequestURL().toString());
		response.setMessage(ex.getMessage());
		return response;
	}
	
	/**************************** 
	 * UserRestController 
	 *****************************/
}
