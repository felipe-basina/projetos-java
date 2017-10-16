package com.github.youmoo.spring.domain;

import java.io.Serializable;

/**
 * @autor youmoo
 * @since 2014-06-14 下午5:01
 */
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2055591028072634753L;
	
	private Integer id;
	private String username;
	private Integer age;

	public User() {
	}

	public User(Integer id, String username, Integer age) {
		this.id = id;
		this.username = username;
		this.age = age;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
}
