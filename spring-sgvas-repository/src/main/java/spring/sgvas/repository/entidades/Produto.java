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
 * The persistent class for the produto database table.
 * 
 */
@Entity
@NamedQuery(name = "Produto.findAll", query = "SELECT p FROM Produto p")
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CD_PRODUTO")
	private int cdProduto;

	@Column(name = "DESC_PRODUTO")
	private String descProduto;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_ATUALIZACAO")
	private Date dtAtualizacao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_CRIACAO")
	private Date dtCriacao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_EXPIRACAO")
	private Date dtExpiracao;

	@Column(name = "NO_PRODUTO")
	private String noProduto;

	private String status;

	@Column(name = "USUARIO_ATUALIZACAO")
	private String usuarioAtualizacao;

	// bi-directional many-to-one association to DetalheProduto
	@OneToMany(mappedBy = "produto")
	private List<DetalheProduto> detalheProdutos;

	// bi-directional many-to-one association to Elegibilidade
	@OneToMany(mappedBy = "produto")
	private List<Elegibilidade> elegibilidades;

	// bi-directional many-to-one association to Pagamento
	@OneToMany(mappedBy = "produto")
	private List<Pagamento> pagamentos;

	// bi-directional many-to-one association to RegraNotificacao
	@OneToMany(mappedBy = "produto")
	private List<RegraNotificacao> regraNotificacaos;

	// bi-directional many-to-one association to SistemaProduto
	@OneToMany(mappedBy = "produto")
	private List<SistemaProduto> sistemaProdutos;

	@OneToMany(mappedBy = "produto")
	private List<Assinatura> assinatura;

	public Produto() {
	}

	public int getCdProduto() {
		return this.cdProduto;
	}

	public void setCdProduto(int cdProduto) {
		this.cdProduto = cdProduto;
	}

	public String getDescProduto() {
		return this.descProduto;
	}

	public void setDescProduto(String descProduto) {
		this.descProduto = descProduto;
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

	public Date getDtExpiracao() {
		return this.dtExpiracao;
	}

	public void setDtExpiracao(Date dtExpiracao) {
		this.dtExpiracao = dtExpiracao;
	}

	public String getNoProduto() {
		return this.noProduto;
	}

	public void setNoProduto(String noProduto) {
		this.noProduto = noProduto;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
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
		detalheProduto.setProduto(this);

		return detalheProduto;
	}

	public DetalheProduto removeDetalheProduto(DetalheProduto detalheProduto) {
		getDetalheProdutos().remove(detalheProduto);
		detalheProduto.setProduto(null);

		return detalheProduto;
	}

	public List<Elegibilidade> getElegibilidades() {
		return this.elegibilidades;
	}

	public void setElegibilidades(List<Elegibilidade> elegibilidades) {
		this.elegibilidades = elegibilidades;
	}

	public Elegibilidade addElegibilidade(Elegibilidade elegibilidade) {
		getElegibilidades().add(elegibilidade);
		elegibilidade.setProduto(this);

		return elegibilidade;
	}

	public Elegibilidade removeElegibilidade(Elegibilidade elegibilidade) {
		getElegibilidades().remove(elegibilidade);
		elegibilidade.setProduto(null);

		return elegibilidade;
	}

	public List<Pagamento> getPagamentos() {
		return this.pagamentos;
	}

	public void setPagamentos(List<Pagamento> pagamentos) {
		this.pagamentos = pagamentos;
	}

	/*public Pagamento addPagamento(Pagamento pagamento) {
		getPagamentos().add(pagamento);
		pagamento.setProduto(this);

		return pagamento;
	}

	public Pagamento removePagamento(Pagamento pagamento) {
		getPagamentos().remove(pagamento);
		pagamento.setProduto(null);

		return pagamento;
	}*/

	public List<RegraNotificacao> getRegraNotificacaos() {
		return this.regraNotificacaos;
	}

	public void setRegraNotificacaos(List<RegraNotificacao> regraNotificacaos) {
		this.regraNotificacaos = regraNotificacaos;
	}

	public RegraNotificacao addRegraNotificacao(
			RegraNotificacao regraNotificacao) {
		getRegraNotificacaos().add(regraNotificacao);
		regraNotificacao.setProduto(this);

		return regraNotificacao;
	}

	public RegraNotificacao removeRegraNotificacao(
			RegraNotificacao regraNotificacao) {
		getRegraNotificacaos().remove(regraNotificacao);
		regraNotificacao.setProduto(null);

		return regraNotificacao;
	}

	public List<SistemaProduto> getSistemaProdutos() {
		return this.sistemaProdutos;
	}

	public void setSistemaProdutos(List<SistemaProduto> sistemaProdutos) {
		this.sistemaProdutos = sistemaProdutos;
	}

	public SistemaProduto addSistemaProduto(SistemaProduto sistemaProduto) {
		getSistemaProdutos().add(sistemaProduto);
		sistemaProduto.setProduto(this);

		return sistemaProduto;
	}

	public SistemaProduto removeSistemaProduto(SistemaProduto sistemaProduto) {
		getSistemaProdutos().remove(sistemaProduto);
		sistemaProduto.setProduto(null);

		return sistemaProduto;
	}

	public List<Assinatura> getAssinatura() {
		return assinatura;
	}

	public void setAssinatura(List<Assinatura> assinatura) {
		this.assinatura = assinatura;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Produto [cdProduto=");
		builder.append(cdProduto);
		builder.append(", descProduto=");
		builder.append(descProduto);
		builder.append(", dtAtualizacao=");
		builder.append(dtAtualizacao);
		builder.append(", dtCriacao=");
		builder.append(dtCriacao);
		builder.append(", dtExpiracao=");
		builder.append(dtExpiracao);
		builder.append(", noProduto=");
		builder.append(noProduto);
		builder.append(", status=");
		builder.append(status);
		builder.append(", usuarioAtualizacao=");
		builder.append(usuarioAtualizacao);
		builder.append("]");
		return builder.toString();
	}

}