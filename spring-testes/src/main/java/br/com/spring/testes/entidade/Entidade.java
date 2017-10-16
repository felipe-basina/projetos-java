package br.com.spring.testes.entidade;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({
	@NamedQuery(name="Entidade.findAll", query="SELECT e FROM Entidade e ORDER BY e.nome DESC")
})
public class Entidade implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1057807986701958053L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String nome;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dhCriacao;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDhCriacao() {
		return dhCriacao;
	}

	public void setDhCriacao(Date dhCriacao) {
		this.dhCriacao = dhCriacao;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Entidade [id=");
		builder.append(id);
		builder.append(", nome=");
		builder.append(nome);
		builder.append(", dhCriacao=");
		builder.append(dhCriacao);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dhCriacao == null) ? 0 : dhCriacao.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Entidade other = (Entidade) obj;
		if (dhCriacao == null) {
			if (other.dhCriacao != null)
				return false;
		} else if (!dhCriacao.equals(other.dhCriacao))
			return false;
		if (id != other.id)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

}
