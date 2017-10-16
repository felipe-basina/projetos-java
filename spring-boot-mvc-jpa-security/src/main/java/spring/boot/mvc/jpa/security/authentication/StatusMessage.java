package spring.boot.mvc.jpa.security.authentication;

import java.io.Serializable;

/**
 * Simple entity to send a error or success message to inform user.
 *
 */
public class StatusMessage implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5240471123980498370L;

	/** The Constant ERROR. */
	public static final String ERROR = "error";

	/** The Constant SUCCESS. */
	public static final String SUCCESS = "success";

	/**
	 * Instantiates a new status message.
	 *
	 * @param pStatus
	 *            the status
	 * @param pMessage
	 *            the message
	 */
	public StatusMessage(final String pStatus, final String pMessage) {
		this.status = pStatus;
		this.message = pMessage;
	}

	/** The status. */
	private String status;

	/** The message. */
	private String message;

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public final String getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param pStatus
	 *            the new status
	 */
	public final void setStatus(final String pStatus) {
		this.status = pStatus;
	}

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public final String getMessage() {
		return message;
	}

	/**
	 * Sets the message.
	 *
	 * @param pMessage
	 *            the new message
	 */
	public final void setMessage(final String pMessage) {
		this.message = pMessage;
	}
}