package spring.sgvas.repository.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "assinatura")
public class Assinatura implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1531453623338232328L;

	@EmbeddedId
	private AssinaturaPK id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_ATUALIZACAO")
	private Date dtAtualizacao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_CRIACAO")
	private Date dtCriacao;

	@Column(name = "USUARIO_ATUALIZACAO")
	private String usuarioAtualizacao;

	@Temporal(TemporalType.DATE)
	@Column(name = "DT_EXPIRACAO")
	private Date dtExpiracao;

	@Column(name = "LISTA_PARAMETROS")
	private String listaParametros;

	@Column(name = "TIPO_SOLICITACAO")
	private String tipoSolicitacao;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "VALOR")
	private Double valor;

	@Column(name = "CD_PAGAMENTO")
	private Integer cdPagamento;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CD_ASSINANTE", insertable = false, updatable = false)
	private Assinante assinante;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CD_MOTIVO_STATUS", insertable = false, updatable = false)
	private MotivoStatus motivoStatus;

	@OneToMany(mappedBy = "assinatura")
	private List<CicloAssinatura> cicloAssinatura;

	@OneToMany(mappedBy = "assinatura")
	private List<ControleNotificacao> controleNotificacao;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CD_PRODUTO", insertable = false, updatable = false)
	private Produto produto;

	public AssinaturaPK getId() {
		return id;
	}

	public void setId(AssinaturaPK id) {
		this.id = id;
	}

	public Date getDtAtualizacao() {
		return dtAtualizacao;
	}

	public void setDtAtualizacao(Date dtAtualizacao) {
		this.dtAtualizacao = dtAtualizacao;
	}

	public Date getDtCriacao() {
		return dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public String getUsuarioAtualizacao() {
		return usuarioAtualizacao;
	}

	public void setUsuarioAtualizacao(String usuarioAtualizacao) {
		this.usuarioAtualizacao = usuarioAtualizacao;
	}

	public Date getDtExpiracao() {
		return dtExpiracao;
	}

	public void setDtExpiracao(Date dtExpiracao) {
		this.dtExpiracao = dtExpiracao;
	}

	public String getListaParametros() {
		return listaParametros;
	}

	public void setListaParametros(String listaParametros) {
		this.listaParametros = listaParametros;
	}

	public String getTipoSolicitacao() {
		return tipoSolicitacao;
	}

	public void setTipoSolicitacao(String tipoSolicitacao) {
		this.tipoSolicitacao = tipoSolicitacao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Assinante getAssinante() {
		return assinante;
	}

	public void setAssinante(Assinante assinante) {
		this.assinante = assinante;
	}

	public MotivoStatus getMotivoStatus() {
		return motivoStatus;
	}

	public void setMotivoStatus(MotivoStatus motivoStatus) {
		this.motivoStatus = motivoStatus;
	}

	public List<CicloAssinatura> getCicloAssinatura() {
		return cicloAssinatura;
	}

	public void setCicloAssinatura(List<CicloAssinatura> cicloAssinatura) {
		this.cicloAssinatura = cicloAssinatura;
	}

	public List<ControleNotificacao> getControleNotificacao() {
		return controleNotificacao;
	}

	public void setControleNotificacao(
			List<ControleNotificacao> controleNotificacao) {
		this.controleNotificacao = controleNotificacao;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Integer getCdPagamento() {
		return cdPagamento;
	}

	public void setCdPagamento(Integer cdPagamento) {
		this.cdPagamento = cdPagamento;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Assinatura [id=");
		builder.append(id);
		builder.append(", dtAtualizacao=");
		builder.append(dtAtualizacao);
		builder.append(", dtCriacao=");
		builder.append(dtCriacao);
		builder.append(", usuarioAtualizacao=");
		builder.append(usuarioAtualizacao);
		builder.append(", dtExpiracao=");
		builder.append(dtExpiracao);
		builder.append(", listaParametros=");
		builder.append(listaParametros);
		builder.append(", tipoSolicitacao=");
		builder.append(tipoSolicitacao);
		builder.append(", status=");
		builder.append(status);
		builder.append(", valor=");
		builder.append(valor);
		builder.append("]");
		return builder.toString();
	}

}
