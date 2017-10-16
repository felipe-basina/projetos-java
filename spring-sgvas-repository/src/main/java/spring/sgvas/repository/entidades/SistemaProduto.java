package spring.sgvas.repository.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the sistema_produto database table.
 * 
 */
@Entity
@Table(name = "sistema_produto")
@NamedQuery(name = "SistemaProduto.findAll", query = "SELECT s FROM SistemaProduto s")
public class SistemaProduto implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SistemaProdutoPK id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_CRIACAO")
	private Date dtCriacao;

	@Column(name = "USUARIO_CRIACAO")
	private String usuarioCriacao;

	// bi-directional many-to-one association to Integracao
	@OneToMany(mappedBy = "sistemaProduto")
	private List<Integracao> integracaos;

	// bi-directional many-to-one association to ServicoSistemaProduto
	@OneToMany(mappedBy = "sistemaProduto")
	private List<ServicoSistemaProduto> servicoSistemaProdutos;

	// bi-directional many-to-one association to Sistema
	@ManyToOne
	@JoinColumn(name = "CD_SISTEMA", insertable = false, updatable = false)
	private Sistema sistema;

	// bi-directional many-to-one association to Produto
	@ManyToOne
	@JoinColumn(name = "CD_PRODUTO", insertable = false, updatable = false)
	private Produto produto;

	public SistemaProduto() {
	}

	public SistemaProdutoPK getId() {
		return this.id;
	}

	public void setId(SistemaProdutoPK id) {
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

	public List<Integracao> getIntegracaos() {
		return this.integracaos;
	}

	public void setIntegracaos(List<Integracao> integracaos) {
		this.integracaos = integracaos;
	}

	public Integracao addIntegracao(Integracao integracao) {
		getIntegracaos().add(integracao);
		integracao.setSistemaProduto(this);

		return integracao;
	}

	public Integracao removeIntegracao(Integracao integracao) {
		getIntegracaos().remove(integracao);
		integracao.setSistemaProduto(null);

		return integracao;
	}

	public List<ServicoSistemaProduto> getServicoSistemaProdutos() {
		return this.servicoSistemaProdutos;
	}

	public void setServicoSistemaProdutos(
			List<ServicoSistemaProduto> servicoSistemaProdutos) {
		this.servicoSistemaProdutos = servicoSistemaProdutos;
	}

	public ServicoSistemaProduto addServicoSistemaProduto(
			ServicoSistemaProduto servicoSistemaProduto) {
		getServicoSistemaProdutos().add(servicoSistemaProduto);
		servicoSistemaProduto.setSistemaProduto(this);

		return servicoSistemaProduto;
	}

	public ServicoSistemaProduto removeServicoSistemaProduto(
			ServicoSistemaProduto servicoSistemaProduto) {
		getServicoSistemaProdutos().remove(servicoSistemaProduto);
		servicoSistemaProduto.setSistemaProduto(null);

		return servicoSistemaProduto;
	}

	public Sistema getSistema() {
		return this.sistema;
	}

	public void setSistema(Sistema sistema) {
		this.sistema = sistema;
	}

	public Produto getProduto() {
		return this.produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

}