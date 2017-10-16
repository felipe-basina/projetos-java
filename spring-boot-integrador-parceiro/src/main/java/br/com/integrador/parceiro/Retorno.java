package br.com.integrador.parceiro;

import java.util.Date;

public class Retorno {

	private long id;

	private String descricao;

	private Date dataHora;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataHora() {
		return dataHora;
	}

	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}

	public Retorno(long id, String descricao, Date dataHora) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.dataHora = dataHora;
	}

}
