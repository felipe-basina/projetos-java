package br.com.sample.spring.ehcache.model;

import java.io.Serializable;

public class Movie implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5612076372767725991L;

	private Integer id;
	
	private String name;
	
	private String directory;

	public Movie(Integer id, String name, String directory) {
		super();
		this.id = id;
		this.name = name;
		this.directory = directory;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Movie [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", directory=");
		builder.append(directory);
		builder.append("]");
		return builder.toString();
	}
	
}
