package br.com.rest.util;

public enum MessageCodeType {

	ERROR_NOT_FOUND ("ERR0001", "Item not found"),
	ERROR_INVALID ("ERR0002", "Invalid item"),
	ERROR_REMOVE ("ERR0003", "Item not removed"),
	ERROR_SAVE ("ERR0004", "Item not saved"),
	ERROR_UPDATE ("ERR0005", "Item not updated"),
	OK_REMOVE ("OK0001", "Item removed"),
	OK_SAVE ("OK0002", "Item saved"),
	OK_UPDATE ("OK0003", "Item updated");
	
	private String code;
	private String description;
	
	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	private MessageCodeType(String code, String description) {
		this.code = code;
		this.description = description;
	}
}
