package spring.sgvas.repository.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the parametro_produto database table.
 * 
 */
@Entity
@Table(name = "parametro_produto")
@NamedQuery(name = "ParametroProduto.findAll", query = "SELECT p FROM ParametroProduto p")
public class ParametroProduto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PARAMETRO_PRODUTO")
	private String parametroProduto;

	@Column(name = "DESC_PARAMETRO_PRODUTO")
	private String descParametroProduto;

	@Column(name = "DT_ATUALIZACAO")
	private String dtAtualizacao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_CRIACAO")
	private Date dtCriacao;

	@Column(name = "USUARIO_ATUALIZACAO")
	private String usuarioAtualizacao;

	// bi-directional many-to-one association to DetalheProduto
	@OneToMany(mappedBy = "parametroProduto")
	private List<DetalheProduto> detalheProdutos;

	public ParametroProduto() {
	}

	public String getParametroProduto() {
		return this.parametroProduto;
	}

	public void setParametroProduto(String parametroProduto) {
		this.parametroProduto = parametroProduto;
	}

	public String getDescParametroProduto() {
		return this.descParametroProduto;
	}

	public void setDescParametroProduto(String descParametroProduto) {
		this.descParametroProduto = descParametroProduto;
	}

	public String getDtAtualizacao() {
		return this.dtAtualizacao;
	}

	public void setDtAtualizacao(String dtAtualizacao) {
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

	public List<DetalheProduto> getDetalheProdutos() {
		return this.detalheProdutos;
	}

	public void setDetalheProdutos(List<DetalheProduto> detalheProdutos) {
		this.detalheProdutos = detalheProdutos;
	}

	public DetalheProduto addDetalheProduto(DetalheProduto detalheProduto) {
		getDetalheProdutos().add(detalheProduto);
		detalheProduto.setParametroProduto(this);

		return detalheProduto;
	}

	public DetalheProduto removeDetalheProduto(DetalheProduto detalheProduto) {
		getDetalheProdutos().remove(detalheProduto);
		detalheProduto.setParametroProduto(null);

		return detalheProduto;
	}

}