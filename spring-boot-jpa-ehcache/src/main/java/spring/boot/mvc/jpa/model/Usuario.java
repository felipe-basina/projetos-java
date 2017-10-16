package spring.boot.mvc.jpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import spring.boot.mvc.jpa.form.model.UsuarioForm;

@Entity(name = "usuario")
//@Table(name = "REGISTRO_TBL", schema = "springboot")
//@Table(name = "REGISTRO_TBL")
public class Usuario /*implements Serializable*/ {

	/**
	 * 
	 */
	//private static final Long serialVersionUID = -6851014382355540256L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDENTIFICADOR")
	private Long id;

	@Column(name = "REGISTRO_NOME")
	private String nome;

	@Column(name = "REGISTRO_ULTIMO_NOME")
	private String ultimoNome;

	@Column(name = "REGISTRO_TELEFONE")
	private String telefone;

	public Usuario() {
		super();
	}

	public Usuario(String nome, String ultimoNome, String telefone) {
		this.nome = nome;
		this.ultimoNome = ultimoNome;
		this.telefone = telefone;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUltimoNome() {
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

	public UsuarioForm getUsuarioFormFromUsuarioEntity() {
		return new UsuarioForm(this.getId(), this.getNome(),
				this.getUltimoNome(), this.getTelefone());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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
