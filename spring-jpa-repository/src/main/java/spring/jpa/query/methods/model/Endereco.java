package spring.jpa.query.methods.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "endereco")
@Table(name = "LOCALIZACAO_TBL", schema = "springboot")
public class Endereco implements Serializable {

	private static final long serialVersionUID = -6851014382355540652L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LOCALIZACAO_IDENTIFICADOR")
	private long id;

	@Column(name = "LOCALIZACAO_RUA")
	private String rua;

	@Column(name = "LOCALIZACAO_CEP")
	private String cep;

	@Column(name = "LOCALIZACAO_CIDADE")
	private String cidade;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDENTIFICADOR", nullable = false)
	private Usuario usuario;

	public Endereco() {
	}

	public Endereco(String rua, String cep, String cidade) {
		this.rua = rua;
		this.cep = cep;
		this.cidade = cidade;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cep == null) ? 0 : cep.hashCode());
		result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((rua == null) ? 0 : rua.hashCode());
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
		Endereco other = (Endereco) obj;
		if (cep == null) {
			if (other.cep != null)
				return false;
		} else if (!cep.equals(other.cep))
			return false;
		if (cidade == null) {
			if (other.cidade != null)
				return false;
		} else if (!cidade.equals(other.cidade))
			return false;
		if (id != other.id)
			return false;
		if (rua == null) {
			if (other.rua != null)
				return false;
		} else if (!rua.equals(other.rua))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Endereco [id=");
		builder.append(id);
		builder.append(", rua=");
		builder.append(rua);
		builder.append(", cep=");
		builder.append(cep);
		builder.append(", cidade=");
		builder.append(cidade);
		builder.append("]");
		return builder.toString();
	}
}
