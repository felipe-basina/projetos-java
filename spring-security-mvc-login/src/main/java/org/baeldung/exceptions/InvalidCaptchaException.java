package org.baeldung.exceptions;

import org.springframework.security.core.AuthenticationException;

public class InvalidCaptchaException extends AuthenticationException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2943977143457710598L;

	/**
	 * Instantiates a new invalid captcha exception.
	 * 
	 * @param message
	 *            the message
	 */
	public InvalidCaptchaException(final String message) {
		super(message);
	}
}