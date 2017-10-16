package br.com.ws.client.model;

public class AddressModel {

	private String address;

	public String getAddress() {
		return address;
	}

	public AddressModel(String address) {
		super();
		this.address = address;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(" ENDPOINT = [");
		builder.append(address);
		builder.append("]");
		return builder.toString();
	}
	
}