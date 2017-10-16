package br.com.rest.controller.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;

import br.com.rest.controller.AbstractClass;
import br.com.rest.form.validation.MessageFormValidationList;

public class ControllerValidationHandlerTest extends AbstractClass {

	@Mock
	private MessageSource msgSourceMock;
	
	@Mock
	private BindingResult bindingResult;
	
	private ControllerValidationHandler ctlValidationHandler;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.ctlValidationHandler = new ControllerValidationHandler(this.msgSourceMock);
	}

	@Test
	public void processValidationErrorMethodArgumentNotValidException() {
		String returnMessageTitle = this.msgSource
				.getMessage("error.title.notnull", null, Locale.getDefault());
		
		String returnMessageDescription = this.msgSource
				.getMessage("error.description.notnull", null, Locale.getDefault());
		
		MethodArgumentNotValidException ex = new MethodArgumentNotValidException(null, 
				this.bindingResult);
		
		List<ObjectError> errorList = new ArrayList<ObjectError>();
		errorList.add(new ObjectError("error.title", "error.title.notnull"));
		errorList.add(new ObjectError("error.description", "error.description.notnull"));
		
		Mockito.when(this.bindingResult.getAllErrors()).thenReturn(errorList);
		
		// First loop iteration
		Mockito.when(this.msgSourceMock.getMessage("error.title.notnull", 
				null, Locale.getDefault())).thenReturn(returnMessageTitle);

		// Second loop iteration
		Mockito.when(this.msgSourceMock.getMessage("error.description.notnull", 
				null, Locale.getDefault())).thenReturn(returnMessageDescription);
		
		MessageFormValidationList validation = this.ctlValidationHandler.processValidationError(ex);
		
		Assert.assertNotNull(validation);
		Assert.assertTrue(validation.getMsgFormValidation().size() > 0);
		Assert.assertTrue(validation.getMsgFormValidation().size() == errorList.size());
		Assert.assertEquals(validation.getMsgFormValidation().get(0).getMessage(), returnMessageTitle);
		Assert.assertEquals(validation.getMsgFormValidation().get(1).getMessage(), returnMessageDescription);
	}

	@Test
	public void processValidationErrorHttpRequestMethodNotSupportedException() {
		String returnMessage = this.msgSource.getMessage("error.method.notallowed", 
				null, Locale.getDefault());
		
		HttpRequestMethodNotSupportedException methodNotSupported = null;
		
		Mockito.when(this.msgSourceMock.getMessage("error.method.notallowed", 
				null, Locale.getDefault())).thenReturn(returnMessage);
		
		MessageFormValidationList validation = this.ctlValidationHandler.processValidationError(methodNotSupported);
		
		Assert.assertNotNull(validation);
		Assert.assertTrue(validation.getMsgFormValidation().size() > 0);
		Assert.assertTrue(validation.getMsgFormValidation().size() == 1);
		Assert.assertEquals(validation.getMsgFormValidation().get(0).getMessage(), returnMessage);		
	}
}
