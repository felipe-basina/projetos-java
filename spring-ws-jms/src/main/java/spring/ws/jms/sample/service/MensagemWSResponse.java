package spring.ws.jms.sample.service;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "mensagem-ws-response")
public class MensagemWSResponse {

	@XmlElement(name = "mensagem-retorno")
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
