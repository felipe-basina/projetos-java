package com.spring.rest.jpa.model;

import com.spring.rest.jpa.enums.MessageType;

public class MessageVO {

	private String message;
	
	private MessageType type;

	public MessageVO() {
		super();
	}

	public MessageVO(String message, MessageType type) {
		super();
		this.message = message;
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}
	
}
