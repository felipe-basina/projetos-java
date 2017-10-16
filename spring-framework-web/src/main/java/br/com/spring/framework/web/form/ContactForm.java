package br.com.spring.framework.web.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ContactForm {

	private Integer id;

	@NotNull
	@Size(min = 4, message = "Campo Obrigat�rio")
	private String nome;
	@NotNull
	@Size(min = 4, message = "Campo Obrigat�rio")
	private String sobreNome;
	@NotNull
	@Size(min = 4, message = "Campo Obrigat�rio")
	private String email;
	@NotNull
	@Size(min = 4, message = "Campo Obrigat�rio")
	private String telefone;

	public ContactForm() {
	}

	public ContactForm(Integer id, String nome, String sobreNome, String email,
			String telefone) {
		super();
		this.id = id;
		this.nome = nome;
		this.sobreNome = sobreNome;
		this.email = email;
		this.telefone = telefone;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobreNome() {
		return sobreNome;
	}

	public void setSobreNome(String sobreNome) {
		this.sobreNome = sobreNome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ContactForm [nome=");
		builder.append(nome);
		builder.append(", sobreNome=");
		builder.append(sobreNome);
		builder.append(", email=");
		builder.append(email);
		builder.append(", telefone=");
		builder.append(telefone);
		builder.append("]");
		return builder.toString();
	}
}
