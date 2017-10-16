package br.com.integrador.parceiro;

public class SmsResponse {

	private String correlationId;

	public String getId() {
		return String.valueOf(System.currentTimeMillis());
	}

	public String getCorrelationId() {
		return correlationId;
	}

	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}

	public SmsResponse(String correlationId) {
		super();
		this.correlationId = correlationId;
	}

}
