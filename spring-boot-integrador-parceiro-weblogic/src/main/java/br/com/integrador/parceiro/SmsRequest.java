package br.com.integrador.parceiro;

public class SmsRequest {

	private String destination;

	private String messageText;

	private String correlationId;

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getMessageText() {
		return messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	public String getCorrelationId() {
		return correlationId;
	}

	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SmsRequest [destination=");
		builder.append(destination);
		builder.append(", messageText=");
		builder.append(messageText);
		builder.append(", correlationId=");
		builder.append(correlationId);
		builder.append("]");
		return builder.toString();
	}

}
