package spring.boot.mvc.bootstrap.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class EmailAlreadyInUseException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1988165608215218254L;

	public EmailAlreadyInUseException() {
		super();
	}
	
	public EmailAlreadyInUseException(String message) {
		super(message);
	}
	
	public EmailAlreadyInUseException(Throwable th) {
		super(th);
	}
	
	public EmailAlreadyInUseException(String message, Throwable th) {
		super(message, th);
	}
	
}
