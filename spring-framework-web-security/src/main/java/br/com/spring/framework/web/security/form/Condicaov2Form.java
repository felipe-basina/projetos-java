package br.com.spring.framework.web.security.form;

public class Condicaov2Form {

	private boolean exibirMensagem;

	private String mensagem;

	private String nome;

	public boolean isExibirMensagem() {
		return exibirMensagem;
	}

	public void setExibirMensagem(boolean exibirMensagem) {
		this.exibirMensagem = exibirMensagem;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
