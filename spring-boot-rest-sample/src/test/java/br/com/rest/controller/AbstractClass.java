package br.com.rest.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.rest.InitApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { InitApplication.class })
public class AbstractClass {
	
	@Autowired
	protected MessageSource msgSource;
	
	@Test
	public void test() {
		Assert.assertNotNull(msgSource);
	}
}
