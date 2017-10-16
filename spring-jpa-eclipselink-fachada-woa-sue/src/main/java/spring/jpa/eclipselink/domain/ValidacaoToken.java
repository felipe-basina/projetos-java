package spring.jpa.eclipselink.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "validacaoToken")
@Table(name = "SUE_VALIDACAO_TOKEN")
public class ValidacaoToken implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CD_COMPOSICAO_TOKEN")
	private String chaveToken;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_CRIACAO")
	private Date dtCriacao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_EXPIRACAO")
	private Date dtExpiracao;

	@Column(name = "VL_CODIGO_AUTORIZADO")
	private String valorToken;

	@Column(name = "QT_TENTATIVAS_INCORRETA")
	private long qtTentativasIncorreta;

	@Column(name = "IN_TIPO_TOKEN")
	private String tipoToken;

	public String getChaveToken() {
		return chaveToken;
	}

	public void setChaveToken(String chaveToken) {
		this.chaveToken = chaveToken;
	}

	public Date getDtCriacao() {
		return dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public Date getDtExpiracao() {
		return dtExpiracao;
	}

	public void setDtExpiracao(Date dtExpiracao) {
		this.dtExpiracao = dtExpiracao;
	}

	public String getValorToken() {
		return valorToken;
	}

	public void setValorToken(String valorToken) {
		this.valorToken = valorToken;
	}

	public long getQtTentativasIncorreta() {
		return qtTentativasIncorreta;
	}

	public void setQtTentativasIncorreta(long qtTentativasIncorreta) {
		this.qtTentativasIncorreta = qtTentativasIncorreta;
	}

	public String getTipoToken() {
		return tipoToken;
	}

	public void setTipoToken(String tipoToken) {
		this.tipoToken = tipoToken;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ValidacaoToken [chaveToken=");
		builder.append(chaveToken);
		builder.append(", dtCriacao=");
		builder.append(dtCriacao);
		builder.append(", dtExpiracao=");
		builder.append(dtExpiracao);
		builder.append(", valorToken=");
		builder.append(valorToken);
		builder.append(", qtTentativasIncorreta=");
		builder.append(qtTentativasIncorreta);
		builder.append(", tipoToken=");
		builder.append(tipoToken);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((chaveToken == null) ? 0 : chaveToken.hashCode());
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
		ValidacaoToken other = (ValidacaoToken) obj;
		if (chaveToken == null) {
			if (other.chaveToken != null)
				return false;
		} else if (!chaveToken.equals(other.chaveToken))
			return false;
		return true;
	}

	@Override
	public ValidacaoToken clone() throws CloneNotSupportedException {

		ValidacaoToken token = new ValidacaoToken();

		token.setChaveToken(this.getChaveToken());
		token.setDtCriacao(this.getDtCriacao());
		token.setDtExpiracao(this.getDtExpiracao());
		token.setQtTentativasIncorreta(this.getQtTentativasIncorreta());
		token.setValorToken(this.getValorToken());
		token.setTipoToken(this.getTipoToken());

		return token;
	}
}
