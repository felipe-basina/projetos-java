package br.com.rest.form.validation;

import br.com.rest.util.MessageFormType;

public class MessageFormValidation {

	private int id;
	private int httpStatus;
	private String message;
	private MessageFormType type;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public MessageFormType getType() {
		return type;
	}

	public void setType(MessageFormType type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public MessageFormValidation() {
		super();
	}

	public int getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(int httpStatus) {
		this.httpStatus = httpStatus;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{id=");
		builder.append(id);
		builder.append(", message=");
		builder.append(message);
		builder.append(", type=");
		builder.append(type.toString());
		builder.append("}");
		return builder.toString();
	}

	public MessageFormValidation(int id, int httpStatus, String message,
			MessageFormType type) {
		super();
		this.id = id;
		this.httpStatus = httpStatus;
		this.message = message;
		this.type = type;
	}
	
}
