package com.spring.rest.jpa.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.spring.rest.jpa.enums.MessageType;
import com.spring.rest.jpa.model.MessageVO;

@ControllerAdvice
public class ControllerValidationHandler {

	@Autowired
	private MessageSource message;

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public List<MessageVO> processValidationError(MethodArgumentNotValidException ex) {
		BindingResult result = ex.getBindingResult();
		
		List<MessageVO> errors = new ArrayList<MessageVO>();
		
		for (FieldError error : result.getFieldErrors()) {
			errors.add(this.processFieldError(error));
		}

		return errors;
	}

	private MessageVO processFieldError(FieldError error) {
		MessageVO mvo = null;
		if (error != null) {
			String msgErroParams = null;
			
			if (error.getDefaultMessage().contains("param")) {
				msgErroParams = message.getMessage(error.getDefaultMessage()
						.concat(".value"), null, LocaleContextHolder.getLocale());
			}
			
			String msg = message.getMessage(error.getDefaultMessage(),
					new Object[] { msgErroParams },
					LocaleContextHolder.getLocale());
			mvo = new MessageVO(msg, MessageType.ERROR);
		}
		return mvo;
	}

}
