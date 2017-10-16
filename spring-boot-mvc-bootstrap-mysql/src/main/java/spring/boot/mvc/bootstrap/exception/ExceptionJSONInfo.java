package spring.boot.mvc.bootstrap.exception;

public class ExceptionJSONInfo {

	private String url;
	private String message;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ExceptionJSONInfo [url=");
		builder.append(url);
		builder.append(", message=");
		builder.append(message);
		builder.append("]");
		return builder.toString();
	}
}