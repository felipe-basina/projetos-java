package br.com.spring.jdbc;

import java.util.Date;

public class Teste {

	private long id;

	private Date dtCadastro;

	private String nome;

	public Teste(Date dtCadastro, String nome) {
		super();
		this.dtCadastro = dtCadastro;
		this.nome = nome;
	}

	public Teste(String nome) {
		super();
		this.nome = nome;
	}

	public Teste() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Teste [id=");
		builder.append(id);
		builder.append(", dtCadastro=");
		builder.append(dtCadastro);
		builder.append(", nome=");
		builder.append(nome);
		builder.append("]");
		return builder.toString();
	}

}