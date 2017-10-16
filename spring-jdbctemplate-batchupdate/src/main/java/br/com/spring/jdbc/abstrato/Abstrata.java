package br.com.spring.jdbc.abstrato;

public abstract class Abstrata {

	private long id = 1l;

	protected long getId() {
		return id;
	}

	protected void setId(long id) {
		this.id = id;
	}
}
