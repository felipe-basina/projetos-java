package br.com.spring.framework.web.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "contato")
public class Contact implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4978627918879855688L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDENTIFICADOR")
	private Integer id;

	@Column(name = "NOME_CONTATO")
	@NotNull
	@Size(min = 4, message = "Campo Obrigat�rio")
	private String nome;

	@Column(name = "ULTIMO_NOME_CONTATO")
	@NotNull
	@Size(min = 4, message = "Campo Obrigat�rio")
	private String sobreNome;

	@Column(name = "EMAIL_CONTATO")
	@NotNull
	@Size(min = 4, message = "Campo Obrigat�rio")
	private String email;

	@Column(name = "TELEFONE_CONTATO")
	@NotNull
	@Size(min = 4, message = "Campo Obrigat�rio")
	private String telefone;

	public Contact() {
	}

	public Contact(String nome, String sobreNome, String email, String telefone) {
		super();
		this.nome = nome;
		this.sobreNome = sobreNome;
		this.email = email;
		this.telefone = telefone;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Contact [id=");
		builder.append(id);
		builder.append(", nome=");
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
