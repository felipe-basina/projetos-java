package br.com.rest.model;

import java.util.Date;

public class Message {

	private String id;
	private int code;
	private String description;
	private Date timestamp;
	private String details;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	public Message(String id, int code, String description, Date timestamp) {
		super();
		this.id = id;
		this.code = code;
		this.description = description;
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Message [id=");
		builder.append(id);
		builder.append(", code=");
		builder.append(code);
		builder.append(", description=");
		builder.append(description);
		builder.append(", timestamp=");
		builder.append(timestamp);
		builder.append(", details=");
		builder.append(details);
		builder.append("]");
		return builder.toString();
	}

}