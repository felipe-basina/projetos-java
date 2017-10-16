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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the servico database table.
 * 
 */
@Entity
@NamedQuery(name = "Servico.findAll", query = "SELECT s FROM Servico s")
public class Servico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CD_SERVICO")
	private String cdServico;

	@Column(name = "DESC_SERVICO")
	private String descServico;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_ATUALIZACAO")
	private Date dtAtualizacao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_CRIACAO")
	private Date dtCriacao;

	@Column(name = "USUARIO_ATUALIZACAO")
	private String usuarioAtualizacao;

	// bi-directional many-to-one association to ServicoSistemaProduto
	@OneToMany(mappedBy = "servico")
	private List<ServicoSistemaProduto> servicoSistemaProdutos;

	public Servico() {
	}

	public String getCdServico() {
		return this.cdServico;
	}

	public void setCdServico(String cdServico) {
		this.cdServico = cdServico;
	}

	public String getDescServico() {
		return this.descServico;
	}

	public void setDescServico(String descServico) {
		this.descServico = descServico;
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
		servicoSistemaProduto.setServico(this);

		return servicoSistemaProduto;
	}

	public ServicoSistemaProduto removeServicoSistemaProduto(
			ServicoSistemaProduto servicoSistemaProduto) {
		getServicoSistemaProdutos().remove(servicoSistemaProduto);
		servicoSistemaProduto.setServico(null);

		return servicoSistemaProduto;
	}

}