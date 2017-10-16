package br.com.ws.client;

import java.util.HashMap;
import java.util.Map;

public class Test {

	private Map<String, Interface> map = new HashMap<String, Interface>();
		
	public Test() {
		map.put("A", new ClassA("valor em A"));
		map.put("B", new ClassB(16));
	}
	
	
	public static void main(String[] args) {
		Test test = new Test();
		
		Interface objA = test.map.get("A");
		System.out.println(objA);
		
		objA.changeValue("outro valor");
		System.out.println(objA);
		
		test = new Test();
		
		Interface objA2 = test.map.get("A");
		System.out.println(objA2);
	}

}

class ClassA implements Interface {
	
	private String string;
	
	public ClassA(String s) {
		this.string = s;
	}

	public void setString(String string) {
		this.string = string;
	}

	public String getString() {
		return string;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Class [string=");
		builder.append(string);
		builder.append("]");
		return builder.toString();
	}
	
	@Override
	public void changeValue(Object o) {
		this.string = (String) o;
	}
}

class ClassB implements Interface {
	
	private Integer integer;
	
	public ClassB(Integer i) {
		this.integer = i;
	}

	public Integer getInteger() {
		return integer;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ClassB [integer=");
		builder.append(integer);
		builder.append("]");
		return builder.toString();
	}
	
	@Override
	public void changeValue(Object o) {
		this.integer = (Integer) o;
	}
	
}

interface Interface {
	void changeValue(Object o);
}