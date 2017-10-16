package spring.ws.jms.sample.service;

public class ParametroInvalidoBusinessException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4474585059618693479L;

	public ParametroInvalidoBusinessException() {
		super();
	}

	public ParametroInvalidoBusinessException(Throwable t) {
		super(t);
	}

	public ParametroInvalidoBusinessException(String message) {
		super(message);
	}

	public ParametroInvalidoBusinessException(String message, Throwable t) {
		super(message, t);
	}
}
