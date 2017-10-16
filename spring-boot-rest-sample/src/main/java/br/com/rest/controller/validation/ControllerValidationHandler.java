package br.com.rest.controller.validation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.rest.form.validation.MessageFormValidation;
import br.com.rest.form.validation.MessageFormValidationList;
import br.com.rest.util.MessageFormType;

@ControllerAdvice
public class ControllerValidationHandler {

	private MessageSource msgSource;

	public ControllerValidationHandler() {
		super();
	}
	
	@Autowired
	public ControllerValidationHandler(MessageSource msgSource) {
		super();
		this.msgSource = msgSource;
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public MessageFormValidationList processValidationError(
			MethodArgumentNotValidException ex) {
		BindingResult result = ex.getBindingResult();
		return this.processFieldError(result.getAllErrors());
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ResponseBody
	public MessageFormValidationList processValidationError(
			HttpRequestMethodNotSupportedException ex) {
		return this.processFieldError();
	}

	private MessageFormValidationList processFieldError() {
		MessageFormValidationList messageErrors = new MessageFormValidationList();
		int index = 0;

		String msg = msgSource.getMessage("error.method.notallowed", null,
				LocaleContextHolder.getLocale());
		
		messageErrors.getMsgFormValidation().add(
				new MessageFormValidation(++index,
						HttpStatus.METHOD_NOT_ALLOWED.value(), msg,
						MessageFormType.ERROR));

		return messageErrors;
	}

	private MessageFormValidationList processFieldError(List<ObjectError> errors) {
		MessageFormValidationList messageErrors = new MessageFormValidationList();

		if (errors != null && errors.size() > 0) {

			int index = 0;

			for (ObjectError error : errors) {
				String msg = msgSource.getMessage(error.getDefaultMessage(),
						null, LocaleContextHolder.getLocale());
				
				messageErrors.getMsgFormValidation().add(
						new MessageFormValidation(++index,
								HttpStatus.BAD_REQUEST.value(), msg,
								MessageFormType.ERROR));
			}
		}

		return messageErrors;
	}
}
