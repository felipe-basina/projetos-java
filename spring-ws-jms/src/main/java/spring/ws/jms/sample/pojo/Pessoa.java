package spring.ws.jms.sample.pojo;

import java.io.Serializable;

public class Pessoa implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3921871855891591682L;

	private String nome;

	private String dtNascimento;

	public Pessoa(String nome, String dtNascimento) {
		this.nome = nome;
		this.dtNascimento = dtNascimento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(String dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Pessoa [nome=");
		builder.append(nome);
		builder.append(", dtNascimento=");
		builder.append(dtNascimento);
		builder.append("]");
		return builder.toString();
	}

}
