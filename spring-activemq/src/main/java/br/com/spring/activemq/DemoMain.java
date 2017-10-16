package br.com.spring.activemq;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DemoMain {
	
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"app-context.xml");
	}

}