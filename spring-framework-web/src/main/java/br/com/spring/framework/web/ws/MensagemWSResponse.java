package br.com.spring.framework.web.ws;

public class MensagemWSResponse {

	private String mensagem;

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MensagemWSResponse [mensagem=");
		builder.append(mensagem);
		builder.append("]");
		return builder.toString();
	}
}
