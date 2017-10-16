package spring.boot.mvc.bootstrap.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1988165608215218254L;

	public UserNotFoundException() {
		super();
	}
	
	public UserNotFoundException(String message) {
		super(message);
	}
	
	public UserNotFoundException(Throwable th) {
		super(th);
	}
	
	public UserNotFoundException(String message, Throwable th) {
		super(message, th);
	}
	
}
