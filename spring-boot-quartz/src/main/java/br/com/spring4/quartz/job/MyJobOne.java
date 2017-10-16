package br.com.spring4.quartz.job;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service("jobone")
public class MyJobOne {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss");
	
	protected void myTask() {
		StringBuilder sb = new StringBuilder(" -######## ");
		sb.append(sdf.format(new Date()))
			.append(" = ")
			.append("This is my task");
		
		System.out.println(sb.toString());
	}
}
