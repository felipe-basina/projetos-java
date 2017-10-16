package spring.jpa.query.methods.model;

import java.util.Calendar;

public class MetaInfo {

	private String aplicacaoId;

	private String operacaoId;

	private String correlacaoId;

	private String enderecoAplicacao;

	private String usuarioId;

	private Calendar dataHora;

	private String canalId;

	public String getAplicacaoId() {
		return aplicacaoId;
	}

	public void setAplicacaoId(String aplicacaoId) {
		this.aplicacaoId = aplicacaoId;
	}

	public String getOperacaoId() {
		return operacaoId;
	}

	public void setOperacaoId(String operacaoId) {
		this.operacaoId = operacaoId;
	}

	public String getCorrelacaoId() {
		return correlacaoId;
	}

	public void setCorrelacaoId(String correlacaoId) {
		this.correlacaoId = correlacaoId;
	}

	public String getEnderecoAplicacao() {
		return enderecoAplicacao;
	}

	public void setEnderecoAplicacao(String enderecoAplicacao) {
		this.enderecoAplicacao = enderecoAplicacao;
	}

	public String getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(String usuarioId) {
		this.usuarioId = usuarioId;
	}

	public Calendar getDataHora() {
		return dataHora;
	}

	public void setDataHora(Calendar dataHora) {
		this.dataHora = dataHora;
	}

	public String getCanalId() {
		return canalId;
	}

	public void setCanalId(String canalId) {
		this.canalId = canalId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MetaInfo [aplicacaoId=");
		builder.append(aplicacaoId);
		builder.append(", operacaoId=");
		builder.append(operacaoId);
		builder.append(", correlacaoId=");
		builder.append(correlacaoId);
		builder.append(", enderecoAplicacao=");
		builder.append(enderecoAplicacao);
		builder.append(", usuarioId=");
		builder.append(usuarioId);
		builder.append(", dataHora=");
		builder.append(dataHora);
		builder.append(", canalId=");
		builder.append(canalId);
		builder.append("]");
		return builder.toString();
	}

}
