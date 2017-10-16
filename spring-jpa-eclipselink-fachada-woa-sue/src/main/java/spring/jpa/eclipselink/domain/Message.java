package spring.jpa.eclipselink.domain;

public class Message {

	private String codigo;
	private String texto;

	public Message() {

	}

	public Message(String code, String text) {
		super();
		this.codigo = code;
		this.texto = text;
	}

	public String getCode() {
		return codigo;
	}

	public void setCode(String code) {
		this.codigo = code;
	}

	public String getText() {
		return texto;
	}

	public void setText(String text) {
		this.texto = text;
	}

}
