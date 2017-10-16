package spring.jpa.eclipselink.domain;

import java.io.Serializable;

public class BusinessResponse<T> implements Serializable {

	private static final long serialVersionUID = 4730607386116534120L;
	private T objectResponse;
	private Message message;
	private Long transactionId;

	public BusinessResponse() {

	}

	public BusinessResponse(T objectResponse, Message message) {
		this.objectResponse = objectResponse;
		this.message = message;
	}

	public BusinessResponse(T objectResponse, Message message,
			Long transactionId) {
		this(objectResponse, message);
		this.transactionId = transactionId;
	}

	public T getObjectResponse() {
		return objectResponse;
	}

	public void setObjectResponse(T objectResponse) {
		this.objectResponse = objectResponse;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

}
