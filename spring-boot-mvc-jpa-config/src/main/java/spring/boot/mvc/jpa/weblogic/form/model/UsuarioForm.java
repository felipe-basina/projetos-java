package spring.boot.mvc.jpa.weblogic.form.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import spring.boot.mvc.jpa.weblogic.model.Usuario;

public class UsuarioForm {

	private long id;

	@NotNull()
	@Size(min = 5, message = "{Size.min.msg}")
	private String nome;

	@NotNull()
	@Size(min = 4, message = "{Size.min.msg}")
	private String ultimoNome;

	@NotEmpty(message = "{NotEmpty.msg}")
	private String telefone;

	public UsuarioForm() {
	}

	public UsuarioForm(long id, String nome, String ultimoNome, String telefone) {
		this.id = id;
		this.nome = nome;
		this.ultimoNome = ultimoNome;
		this.telefone = telefone;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		if (nome != null) {
			return nome.toUpperCase();
		}
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUltimoNome() {
		if (ultimoNome != null) {
			return ultimoNome.toUpperCase();
		}
		return ultimoNome;
	}

	public void setUltimoNome(String ultimoNome) {
		this.ultimoNome = ultimoNome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Usuario getUsuarioEntityFromUsuarioForm() {
		return new Usuario(null, this.getNome().toUpperCase(), this
				.getUltimoNome().toUpperCase(), this.getTelefone());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result
				+ ((telefone == null) ? 0 : telefone.hashCode());
		result = prime * result
				+ ((ultimoNome == null) ? 0 : ultimoNome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioForm other = (UsuarioForm) obj;
		if (id != other.id)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (telefone == null) {
			if (other.telefone != null)
				return false;
		} else if (!telefone.equals(other.telefone))
			return false;
		if (ultimoNome == null) {
			if (other.ultimoNome != null)
				return false;
		} else if (!ultimoNome.equals(other.ultimoNome))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Usuario [id=");
		builder.append(id);
		builder.append(", nome=");
		builder.append(nome);
		builder.append(", ultimoNome=");
		builder.append(ultimoNome);
		builder.append(", telefone=");
		builder.append(telefone);
		builder.append("]");
		return builder.toString();
	}
}
