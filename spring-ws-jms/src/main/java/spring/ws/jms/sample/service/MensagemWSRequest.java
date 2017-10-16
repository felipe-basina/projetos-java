package spring.ws.jms.sample.service;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "mensagem-ws-request")
public class MensagemWSRequest {

	@XmlElement(required = true, nillable = false, name = "nome-usuario")
	@Size(min = 4, message = "Campo Obrigatorio")
	private String nome;

	@XmlElement(required = true, nillable = false, name = "data-nascimento")
	@Size(min = 4, max = 12, message = "Campo Obrigatorio")
	private String dtNascimento;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(String dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MensagemWSRequest [nome=");
		builder.append(nome);
		builder.append(", dtNascimento=");
		builder.append(dtNascimento);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dtNascimento == null) ? 0 : dtNascimento.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		MensagemWSRequest other = (MensagemWSRequest) obj;
		if (dtNascimento == null) {
			if (other.dtNascimento != null)
				return false;
		} else if (!dtNascimento.equals(other.dtNascimento))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
}
