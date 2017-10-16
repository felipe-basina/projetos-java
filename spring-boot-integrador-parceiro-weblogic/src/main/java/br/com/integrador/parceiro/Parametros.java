package br.com.integrador.parceiro;

public enum Parametros {

	URI_INTEGRADOR ("http://localhost:7004/integrador/sms/enviar"),
	
	CONTENT_TYPE_HEADER_PARAM ("content-type"),
	
	CONTENT_TYPE_HEADER_VALUE ("application/json; charset=UTF-8"),
	
	ACCEPT_HEADER_PARAM ("accept"),
	
	ACCEPT_HEADER_VALUE ("application/json"),
	
	USERNAME_HEADER_PARAM ("UserName"),
	
	AUTHENTICATION_TOKEN_HEADER_PARAM ("AuthenticationToken"),
	
	USERNAME_HEADER_VALUE ("felipe@claro.com.br"),
	
	AUTHENTICATION_TOKEN_HEADER_VALUE ("9cb87d36-79af-11e5-89f3-1b0591cdf807"),
	
	;
	
	private String valor;
	
	private Parametros(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}
	
}