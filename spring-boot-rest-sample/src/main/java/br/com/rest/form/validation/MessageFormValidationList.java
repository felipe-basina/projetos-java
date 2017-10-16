package br.com.rest.form.validation;

import java.util.ArrayList;
import java.util.List;

public class MessageFormValidationList {

	private List<MessageFormValidation> msgFormValidation = new ArrayList<MessageFormValidation>();
	
	private int totalErrors;

	public List<MessageFormValidation> getMsgFormValidation() {
		return msgFormValidation;
	}

	public int getTotalErrors() {
		totalErrors = msgFormValidation.size();
		return totalErrors;
	}
	
}