package org.baeldung.exceptions;

import org.springframework.security.core.AuthenticationException;

public class UserAuthenticationException extends AuthenticationException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new user not found exception.
	 *
	 * @param msg the msg
	 * @param t the t
	 */
	public UserAuthenticationException(final String msg, final Throwable t) {
		super(msg, t);
	}

	/**
	 * Instantiates a new user authentication exception.
	 *
	 * @param message the message
	 */
	public UserAuthenticationException(final String message) {
		super(message);
	}

}
