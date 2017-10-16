package spring.jpa.eclipselink.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import spring.jpa.eclipselink.utils.ConfigEnum;

@Entity(name = "parametroSistema")
@Table(name = "SUE_PARAMETRO_SISTEMA")
public class ParametroSistema implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "NO_PARAMETRO")
	@Enumerated(EnumType.STRING)
	private ConfigEnum noParametro;

	@Column(name = "CD_USUARIO_CRIACAO")
	private String usuarioCriacao;

	@Column(name = "CD_USUARIO_MANUT")
	private String usuarioManutencao;

	@Column(name = "VL_PARAMETRO")
	private String vlParametro;

	@Column(name = "DS_PARAMETRO")
	private String dsParametro;

	@Column(name = "IN_CANAL_INTERFACE_APLICACAO")
	private String aplicacao;

	public ConfigEnum getNoParametro() {
		return noParametro;
	}

	public void setNoParametro(ConfigEnum noParametro) {
		this.noParametro = noParametro;
	}

	public String getUsuarioCriacao() {
		return usuarioCriacao;
	}

	public void setUsuarioCriacao(String usuarioCriacao) {
		this.usuarioCriacao = usuarioCriacao;
	}

	public String getUsuarioManutencao() {
		return usuarioManutencao;
	}

	public void setUsuarioManutencao(String usuarioManutencao) {
		this.usuarioManutencao = usuarioManutencao;
	}

	public String getVlParametro() {
		return vlParametro;
	}

	public void setVlParametro(String vlParametro) {
		this.vlParametro = vlParametro;
	}

	public String getDsParametro() {
		return dsParametro;
	}

	public void setDsParametro(String dsParametro) {
		this.dsParametro = dsParametro;
	}

	public String getAplicacao() {
		return aplicacao;
	}

	public void setAplicacao(String aplicacao) {
		this.aplicacao = aplicacao;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ParametroSistema [noParametro=");
		builder.append(noParametro);
		builder.append(", usuarioCriacao=");
		builder.append(usuarioCriacao);
		builder.append(", usuarioManutencao=");
		builder.append(usuarioManutencao);
		builder.append(", vlParametro=");
		builder.append(vlParametro);
		builder.append(", dsParametro=");
		builder.append(dsParametro);
		builder.append(", aplicacao=");
		builder.append(aplicacao);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((noParametro == null) ? 0 : noParametro.hashCode());
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
		ParametroSistema other = (ParametroSistema) obj;
		if (noParametro == null) {
			if (other.noParametro != null)
				return false;
		} else if (!noParametro.equals(other.noParametro))
			return false;
		return true;
	}
}
