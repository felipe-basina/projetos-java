package spring.jpa.eclipselink.exception;

public class DataException extends Exception {

	private static final long serialVersionUID = 841292863469790962L;

	public DataException() {
	}

	public DataException(String message) {
		super(message);
	}

	public DataException(Throwable cause) {
		super(cause);
	}

	public DataException(String message, Throwable cause) {
		super(message, cause);
	}

}
