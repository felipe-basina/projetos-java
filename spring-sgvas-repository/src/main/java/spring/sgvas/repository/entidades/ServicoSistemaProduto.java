package spring.sgvas.repository.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the servico_sistema_produto database table.
 * 
 */
@Entity
@Table(name = "servico_sistema_produto")
@NamedQuery(name = "ServicoSistemaProduto.findAll", query = "SELECT s FROM ServicoSistemaProduto s")
public class ServicoSistemaProduto implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ServicoSistemaProdutoPK id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_CRIACAO")
	private Date dtCriacao;

	@Column(name = "USUARIO_CRIACAO")
	private String usuarioCriacao;

	// bi-directional many-to-one association to SistemaProduto
	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "CD_PRODUTO", referencedColumnName = "CD_PRODUTO", insertable = false, updatable = false),
			@JoinColumn(name = "CD_SISTEMA", referencedColumnName = "CD_SISTEMA", insertable = false, updatable = false) })
	private SistemaProduto sistemaProduto;

	// bi-directional many-to-one association to Servico
	@ManyToOne
	@JoinColumn(name = "CD_SERVICO", insertable = false, updatable = false)
	private Servico servico;

	public ServicoSistemaProduto() {
	}

	public ServicoSistemaProdutoPK getId() {
		return this.id;
	}

	public void setId(ServicoSistemaProdutoPK id) {
		this.id = id;
	}

	public Date getDtCriacao() {
		return this.dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public String getUsuarioCriacao() {
		return this.usuarioCriacao;
	}

	public void setUsuarioCriacao(String usuarioCriacao) {
		this.usuarioCriacao = usuarioCriacao;
	}

	public SistemaProduto getSistemaProduto() {
		return this.sistemaProduto;
	}

	public void setSistemaProduto(SistemaProduto sistemaProduto) {
		this.sistemaProduto = sistemaProduto;
	}

	public Servico getServico() {
		return this.servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

}