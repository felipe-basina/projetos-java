package org.baeldung.util;


public class CaptchaContextHolder {
	/**
	 * Instantiates a new captcha context holder.
	 */
	private CaptchaContextHolder() {
	}

	/** The Constant value. */
	private static final ThreadLocal<String> VALUE = new ThreadLocal<String>();

	private static final ThreadLocal<String> RESPONSE = new ThreadLocal<String>();

	/** The Constant valid. */
	private static final ThreadLocal<Boolean> VALID = new ThreadLocal<Boolean>();

	/**
	 * Sets the value.
	 *
	 * @param response
	 *            the new value
	 */
	public static void setResponse(final String response) {
		CaptchaContextHolder.RESPONSE.set(response);
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public static String getResponse() {
		return RESPONSE.get();
	}

	/**
	 * Sets the value.
	 *
	 * @param value
	 *            the new value
	 */
	public static void setValue(final String value) {
		CaptchaContextHolder.VALUE.set(value);
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public static String getValue() {
		return VALUE.get();
	}

	/**
	 * Checks if is valid.
	 *
	 * @return true, if is valid
	 */
	public static boolean isValid() {
		System.out.println("\n >>>>>>>>>>> CaptchaContextHolder.VALID.get() != null: " + CaptchaContextHolder.VALID.get() != null
				+ " CaptchaContextHolder.VALID.get(): " + CaptchaContextHolder.VALID.get());
		return CaptchaContextHolder.VALID.get() != null && CaptchaContextHolder.VALID.get();
	}

	/**
	 * Sets the valid.
	 *
	 * @param valid
	 *            the new valid
	 */
	public static void setValid(final Boolean valid) {
		CaptchaContextHolder.VALID.set(valid);
	}
}
