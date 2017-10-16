package spring.sgvas.repository.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the detalhe_produto database table.
 * 
 */
@Entity
@Table(name = "detalhe_produto")
@NamedQuery(name = "DetalheProduto.findAll", query = "SELECT d FROM DetalheProduto d")
public class DetalheProduto implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DetalheProdutoPK id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_ATUALIZACAO")
	private Date dtAtualizacao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_CRIACAO")
	private Date dtCriacao;

	@Column(name = "USUARIO_ATUALIZACAO")
	private String usuarioAtualizacao;

	@Column(name = "VL_PARAMETRO_PRODUTO")
	private String vlParametroProduto;

	// bi-directional many-to-one association to Produto
	@ManyToOne
	@JoinColumn(name = "CD_PRODUTO", insertable = false, updatable = false)
	private Produto produto;

	// bi-directional many-to-one association to ParametroProduto
	@ManyToOne
	@JoinColumn(name = "NO_PARAMETRO_PRODUTO", insertable = false, updatable = false)
	private ParametroProduto parametroProduto;

	public DetalheProduto() {
	}

	public DetalheProdutoPK getId() {
		return this.id;
	}

	public void setId(DetalheProdutoPK id) {
		this.id = id;
	}

	public Date getDtAtualizacao() {
		return this.dtAtualizacao;
	}

	public void setDtAtualizacao(Date dtAtualizacao) {
		this.dtAtualizacao = dtAtualizacao;
	}

	public Date getDtCriacao() {
		return this.dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public String getUsuarioAtualizacao() {
		return this.usuarioAtualizacao;
	}

	public void setUsuarioAtualizacao(String usuarioAtualizacao) {
		this.usuarioAtualizacao = usuarioAtualizacao;
	}

	public String getVlParametroProduto() {
		return this.vlParametroProduto;
	}

	public void setVlParametroProduto(String vlParametroProduto) {
		this.vlParametroProduto = vlParametroProduto;
	}

	public Produto getProduto() {
		return this.produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public ParametroProduto getParametroProduto() {
		return this.parametroProduto;
	}

	public void setParametroProduto(ParametroProduto parametroProduto) {
		this.parametroProduto = parametroProduto;
	}

}